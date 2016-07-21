/**
* Copyright (C) 2008 Happy Fish / YuQing
*
* FastDHT Java Client may be copied only under the terms of the GNU Lesser
* General Public License (LGPL).
* Please visit the FastDHT Home Page http://fastdht.csource.org/ for more detail.
**/

package org.csource.fastdht;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.util.Arrays;

/**
* protocol common functions
* @author Happy Fish / YuQing
* @version Version 1.03
*/
public class ProtoCommon
{
	/**
	* package header info
	*/
	public static class PkgHeader
	{
		public int body_len;
		public int key_hash_code;
		public int timestamp;
		public int expires;
		public byte cmd;
		public byte keep_alive;
		public byte status;
		
		public PkgHeader()
		{
		}
		
		public PkgHeader(byte status, int body_len)
		{
			this.status = status;
			this.body_len = body_len;
		}
	}
	
	/**
	* package info
	*/
	public static class PkgInfo
	{
		public PkgHeader header;
		public byte[] body;
		
		public PkgInfo(PkgHeader header, byte[] body)
		{
			this.header = header;
			this.body = body;
		}
	}
	
	public static final byte FDHT_PROTO_CMD_QUIT = 10;
	public static final byte FDHT_PROTO_CMD_SET  = 11;
	public static final byte FDHT_PROTO_CMD_INC  = 12;
	public static final byte FDHT_PROTO_CMD_GET  = 13;
	public static final byte FDHT_PROTO_CMD_DEL  = 14;
	public static final byte FDHT_PROTO_CMD_BATCH_SET  = 15;
	public static final byte FDHT_PROTO_CMD_BATCH_GET  = 16;
	public static final byte FDHT_PROTO_CMD_BATCH_DEL  = 17;
	public static final byte FDHT_PROTO_CMD_STAT       = 18;
	public static final byte FDHT_PROTO_CMD_GET_SUB_KEYS = 19;
	public static final byte FDHT_PROTO_CMD_HEART_BEAT   = 30;
	public static final byte FDHT_PROTO_CMD_RESP         = 40;
	
	public static final int PROTO_HEADER_BODY_LEN_INDEX = 0;
	public static final int PROTO_HEADER_KEY_HASH_CODE_INDEX = 4;
	public static final int PROTO_HEADER_TIMESTAMP_INDEX = 8;
	public static final int PROTO_HEADER_EXPIRES_INDEX = 12;
	public static final int PROTO_HEADER_CMD_INDEX = 16;
	public static final int PROTO_HEADER_KEEP_ALIVE_INDEX = 17;
	public static final int PROTO_HEADER_STATUS_INDEX = 18;
	public static final int FDHT_PROTO_PKG_HEADER_SIZE = 19;
	
	public static final int  FDHT_MAX_NAMESPACE_LEN  = 64;
	public static final int  FDHT_MAX_OBJECT_ID_LEN  = 128;
	public static final int  FDHT_MAX_SUB_KEY_LEN    = 128;
	public static final byte FDHT_FULL_KEY_SEPERATOR = 0x1;
	
	public static final char FDHT_KEY_LIST_SEP_CHAR  = FDHT_FULL_KEY_SEPERATOR;
	public static final String FDHT_KEY_LIST_SEP_STR  = String.valueOf(FDHT_KEY_LIST_SEP_CHAR);
	
	public static final int  FDHT_EXPIRES_NEVER      = 0;  //never timeout
	public static final int  FDHT_EXPIRES_NONE       = -1; //invalid timeout, should ignore
	
	public static final int  FDHT_MAX_FULL_KEY_LEN  = (FDHT_MAX_NAMESPACE_LEN + 1 + 
	                        FDHT_MAX_OBJECT_ID_LEN + 1 + FDHT_MAX_SUB_KEY_LEN);

	private ProtoCommon()
	{
	}

/**
* pack header by FastDHT transfer protocol
* @param header package header
* @param bs byte buff
*/
	public static void packHeader(PkgHeader header, byte[] bs) throws UnsupportedEncodingException
	{
		Arrays.fill(bs, 0, FDHT_PROTO_PKG_HEADER_SIZE, (byte)0);
		
		ProtoCommon.int2buff(header.body_len, bs,PROTO_HEADER_BODY_LEN_INDEX);
		ProtoCommon.int2buff(header.key_hash_code, bs, PROTO_HEADER_KEY_HASH_CODE_INDEX);
		ProtoCommon.int2buff(header.timestamp, bs, PROTO_HEADER_TIMESTAMP_INDEX);
		ProtoCommon.int2buff(header.expires, bs, PROTO_HEADER_EXPIRES_INDEX);
		bs[PROTO_HEADER_CMD_INDEX] = header.cmd;
		bs[PROTO_HEADER_KEEP_ALIVE_INDEX] = header.keep_alive;
		bs[PROTO_HEADER_STATUS_INDEX] = header.status;
		
		return;
	}

/**
* receive pack header
* @param in input stream
* @param expect_cmd expect response command
* @param expect_body_len expect response package body length
* @return PkgHeader: status and pkg body length
*/
	public static PkgHeader recvHeader(InputStream in, byte expect_cmd, long expect_body_len) throws IOException
	{
		PkgHeader header;
		byte[] bs;
		int bytes;
		
		header = new PkgHeader();
		bs = new byte[FDHT_PROTO_PKG_HEADER_SIZE];
		
		if ((bytes=in.read(bs)) != bs.length)
		{
			throw new IOException("recv package size " + bytes + " != " + bs.length);
		}
		
		header.cmd = bs[PROTO_HEADER_CMD_INDEX];
		if (header.cmd != expect_cmd)
		{
			throw new IOException("recv cmd: " + bs[PROTO_HEADER_CMD_INDEX] + " is not correct, expect cmd: " + expect_cmd);
		}
		
		header.status = bs[PROTO_HEADER_STATUS_INDEX];
		header.body_len = ProtoCommon.buff2int(bs, PROTO_HEADER_BODY_LEN_INDEX);
		if (header.body_len < 0)
		{
			throw new IOException("recv body length: " + header.body_len + " < 0!");
		}
		
		if (header.status == 0)
		{
			if (expect_body_len >= 0 && header.body_len != expect_body_len)
			{
				throw new IOException("recv body length: " + header.body_len + " is not correct, expect length: " + expect_body_len);
			}
			
			header.timestamp = ProtoCommon.buff2int(bs, PROTO_HEADER_TIMESTAMP_INDEX);
			header.expires = ProtoCommon.buff2int(bs, PROTO_HEADER_EXPIRES_INDEX);
		}
	
		return header;
	}

/**
* receive whole pack
* @param in input stream
* @param expect_cmd expect response command
* @param expect_body_len expect response package body length
* @return PkgInfo: status and reponse body(byte buff)
*/
	public static PkgInfo recvPackage(InputStream in, byte expect_cmd, long expect_body_len) throws IOException
	{
		PkgHeader header = recvHeader(in, expect_cmd, expect_body_len);
		if (header.status != 0)
		{
			return new PkgInfo(header, null);
		}
		
		byte[] body = new byte[header.body_len];
		int totalBytes = 0;
		int remainBytes = header.body_len;
		int bytes;
		
		while (totalBytes < header.body_len)
		{
			if ((bytes=in.read(body, totalBytes, remainBytes)) < 0)
			{
				break;
			}
			
			totalBytes += bytes;
			remainBytes -= bytes;
		}
		
		if (totalBytes != header.body_len)
		{
			throw new IOException("recv package size " + totalBytes + " != " + header.body_len);
		}
		
		return new PkgInfo(header, body);
	}

/**
* send quit command to server and close socket
* @param sock the Socket object
*/
	public static void quit(Socket sock) throws IOException
	{
		PkgHeader header;
		byte[] bs = new byte[FDHT_PROTO_PKG_HEADER_SIZE];
		
		header = new PkgHeader();
		header.cmd = FDHT_PROTO_CMD_QUIT;
		packHeader(header, bs);
		sock.getOutputStream().write(bs);
		sock.close();
	}
	
/**
* int convert to buff (big-endian)
* @param n number
* @return 4 bytes buff
*/
	public static byte[] int2buff(int n)
	{
		byte[] bs;
		
		bs = new byte[4];
		bs[0] = (byte)((n >> 24) & 0xFF);
		bs[1] = (byte)((n >> 16) & 0xFF);
		bs[2] = (byte)((n >> 8) & 0xFF);
		bs[3] = (byte)(n & 0xFF);
		
		return bs;
	}

/**
* int convert to buff (big-endian)
* @param n number
* @param bs byte buff
* @param offset buff start index
*/
	public static void int2buff(int n, byte[] bs, int offset)
	{
		bs[offset] = (byte)((n >> 24) & 0xFF);
		bs[offset+1] = (byte)((n >> 16) & 0xFF);
		bs[offset+2] = (byte)((n >> 8) & 0xFF);
		bs[offset+3] = (byte)(n & 0xFF);
		
		return;
	}
	
/**
* big-endian buff convert to int
* @param bs 4 bytes buff
* @param offset start index
* @return int number
*/
	public static int buff2int(byte[] bs, int offset)
	{
		return  (((int)(bs[offset] >= 0 ? bs[offset] : 256+bs[offset])) << 24) | 
		        (((int)(bs[offset+1] >= 0 ? bs[offset+1] : 256+bs[offset+1])) << 16) | 
		        (((int)(bs[offset+2] >= 0 ? bs[offset+2] : 256+bs[offset+2])) <<  8) |
		         ((int)(bs[offset+3] >= 0 ? bs[offset+3] : 256+bs[offset+3]));
	}
}
