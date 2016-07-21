/**
* Copyright (C) 2008 Happy Fish / YuQing
*
* FastDHT Java Client may be copied only under the terms of the GNU Lesser
* General Public License (LGPL).
* Please visit the FastDHT Home Page http://fastdht.csource.org/ for more detail.
**/

package org.csource.fastdht;

import java.io.UnsupportedEncodingException;

import org.csource.common.Hash;
import org.csource.common.MyException;

/**
* FastDHT key info
* @author Happy Fish / YuQing
* @version Version 1.05
*/
public class ObjectInfo
{
	protected byte[] namespace;
	protected byte[] object_id;
	protected int namespace_len;
	protected int obj_id_len;
	
	public ObjectInfo(byte[] namespace, byte[] object_id) throws UnsupportedEncodingException, MyException
	{
		this.namespace = namespace;
		this.object_id = object_id;
		this.check();
	}
	
	public ObjectInfo(String namespace, String object_id) throws UnsupportedEncodingException, MyException
	{
		this.namespace = namespace.getBytes(ClientGlobal.g_charset);
		this.object_id = object_id.getBytes(ClientGlobal.g_charset);
		this.check();
	}

	private void check() throws MyException
	{
    if (this.namespace.length > ProtoCommon.FDHT_MAX_NAMESPACE_LEN)
    {
    	namespace_len = ProtoCommon.FDHT_MAX_NAMESPACE_LEN;
    }
    else
    {
    	namespace_len = this.namespace.length;
    }

    if (this.object_id.length > ProtoCommon.FDHT_MAX_OBJECT_ID_LEN)
    {
    	obj_id_len = ProtoCommon.FDHT_MAX_OBJECT_ID_LEN;
    }
    else
    {
    	obj_id_len = this.object_id.length;
    }
        
    if (namespace_len == 0 || obj_id_len == 0)
    {
      throw new MyException("Invalid namespace length: " + this.namespace.length
      						 + " and object ID length: " + this.object_id.length);
    }
	}
	
	public byte[] getNamespace()
	{
		return this.namespace;
	}
	
	public byte[] getObjectId()
	{
		return this.object_id;
	}
		
	@SuppressWarnings("unused")
	public int getHashCode()
	{
		int key_hash_code;
		byte[] hash_key;
		int true_key_len;
		  
		hash_key = new byte[namespace_len + 1 + obj_id_len];
		System.arraycopy(this.namespace, 0, hash_key, 0, namespace_len);
		hash_key[namespace_len] = ProtoCommon.FDHT_FULL_KEY_SEPERATOR;
		System.arraycopy(this.object_id, 0, hash_key, namespace_len + 1, obj_id_len);
  
    key_hash_code = Hash.Time33Hash(hash_key, 0, hash_key.length);
    if (key_hash_code < 0)
    {
            key_hash_code &= 0x7FFFFFFF;
    }
    
    return key_hash_code;
   }
   
   public int getPackLength()
   {
   	return 8 + namespace_len + obj_id_len;
   }

   public int pack(byte[] bsPackage, int offset)
   {  	
   	ProtoCommon.int2buff(this.namespace_len, bsPackage, offset);
   	offset += 4;
 		System.arraycopy(this.namespace, 0, bsPackage, offset, this.namespace_len);
 		offset += this.namespace_len;
   	
   	ProtoCommon.int2buff(this.obj_id_len, bsPackage, offset);
   	offset += 4;
 		System.arraycopy(this.object_id, 0, bsPackage, offset, this.obj_id_len);
 		offset += this.obj_id_len;
   	
 		return offset;
   }
}
