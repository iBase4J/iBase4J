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
public class KeyInfo
{
	protected byte[] namespace;
	protected byte[] object_id;
	protected byte[] key;
	protected int namespace_len;
	protected int obj_id_len;
	protected int key_len;
	
	public KeyInfo(byte[] namespace, byte[] object_id, byte[] key) throws UnsupportedEncodingException, MyException
	{
		this.namespace = namespace;
		this.object_id = object_id;
		this.key = key;
		this.check();
	}
	
	public KeyInfo(String namespace, String object_id, String key) throws UnsupportedEncodingException, MyException
	{
		this.namespace = namespace.getBytes(ClientGlobal.g_charset);
		this.object_id = object_id.getBytes(ClientGlobal.g_charset);
		this.key = key.getBytes(ClientGlobal.g_charset);
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

    if (this.key.length > ProtoCommon.FDHT_MAX_SUB_KEY_LEN)
    {
    	key_len = ProtoCommon.FDHT_MAX_SUB_KEY_LEN;
    }
    else
    {
    	key_len = this.key.length;
    }
    
    if ((namespace_len == 0 && obj_id_len != 0) || 
        (namespace_len != 0 && obj_id_len == 0))
    {
      throw new MyException("Invalid namespace length: " + this.namespace.length
      						 + " and object ID length: " + this.object_id.length);
    }
    
    if (key_len == 0)
    {
      throw new MyException("Invalid key length: " + this.key.length);
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
	
	public byte[] getKey()
	{
		return this.key;
	}
	
	public int getHashCode()
	{
		int key_hash_code;
		byte[] hash_key;
		int true_key_len;
		
    if (namespace_len == 0 && obj_id_len == 0)
    {
        hash_key = this.key;
        true_key_len = this.key_len;
    }
    else
    {
    		hash_key = new byte[namespace_len + 1 + obj_id_len];
    		System.arraycopy(this.namespace, 0, hash_key, 0, namespace_len);
    		hash_key[namespace_len] = ProtoCommon.FDHT_FULL_KEY_SEPERATOR;
    		System.arraycopy(this.object_id, 0, hash_key, namespace_len + 1, obj_id_len);
    		true_key_len = hash_key.length;
    }

    key_hash_code = Hash.Time33Hash(hash_key, 0, true_key_len);
    if (key_hash_code < 0)
    {
            key_hash_code &= 0x7FFFFFFF;
    }
    
    return key_hash_code;
   }
   
   public int getPackLength()
   {
   	return 12 + namespace_len + obj_id_len + key_len;
   }

   public int pack(byte[] bsPackage, int offset)
   {
   	ProtoCommon.int2buff(this.namespace_len, bsPackage, offset);
   	offset += 4;
   	if (this.namespace_len > 0)
   	{
   		System.arraycopy(this.namespace, 0, bsPackage, offset, this.namespace_len);
   		offset += this.namespace_len;
   	}
   	
   	ProtoCommon.int2buff(this.obj_id_len, bsPackage, offset);
   	offset += 4;
   	if (this.obj_id_len > 0)
   	{
   		System.arraycopy(this.object_id, 0, bsPackage, offset, this.obj_id_len);
   		offset += this.obj_id_len;
   	}
   	
   	ProtoCommon.int2buff(this.key_len, bsPackage, offset);
   	offset += 4;
 		System.arraycopy(this.key, 0, bsPackage, offset, this.key_len);
 		offset += this.key_len;
 		return offset;
   }
}
