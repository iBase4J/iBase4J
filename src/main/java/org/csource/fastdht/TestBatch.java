/**
* Copyright (C) 2008 Happy Fish / YuQing
*
* FastDHT Java Client may be copied only under the terms of the GNU Lesser
* General Public License (LGPL).
* Please visit the FastDHT Home Page http://fastdht.csource.org/ for more detail.
**/

package org.csource.fastdht;

import java.util.Arrays;
import java.util.Hashtable;

/**
* client batch test
* @author Happy Fish / YuQing
* @version Version 1.03
*/
public class TestBatch
{
	private TestBatch()
	{
	}
	
	/**
	* entry point
	* @param args comand arguments
	*     <ul><li>args[0]: config filename</li></ul>
	*/
  @SuppressWarnings({ "rawtypes", "unchecked" })
public static void main(String args[])
  {
  	if (args.length < 1)
  	{
  		System.out.println("Usage: java org.csource.fastdht.TestBatch <config filename>");
  		return;
  	}
  	
  	System.out.println("java.version=" + System.getProperty("java.version"));
  	
  	String conf_filename = args[0];
  	try
  	{
  		ClientGlobal.init(conf_filename);
  		FastDHTClient client;
  		ObjectInfo objInfo;
  		
  		System.out.println("network_timeout=" + ClientGlobal.g_network_timeout + "ms");
  		System.out.println("charset=" + ClientGlobal.g_charset);
  		
  		ClientGlobal.g_server_group.print();
  		
  		client = new FastDHTClient(true);
  		try
  		{
	  		objInfo = new ObjectInfo("bbs", "test");
	  		Hashtable keyValues;
	  		Hashtable failKeys;
	  		String[] keys;
	  		
	  		keyValues = new Hashtable();
	  		failKeys = new Hashtable();
	  		keyValues.put("reg", "1236939402");
	  		keyValues.put("login", "happy_fish100");
	  		keyValues.put("intl", "CN");
	  		keyValues.put("co", "China");
	  		
	  		keys = new String[4];
	  		keys[0] = "reg";
	  		keys[1] = "co";
	  		keys[2] = "login";
	  		keys[3] = "intl";
	  		
	  		
	  		System.out.println("batch set: " + client.batchSet(objInfo, keyValues, failKeys, (int)(System.currentTimeMillis() / 1000) + 300) + failKeys);
	  		System.out.println("batch get: " + client.batchGet(objInfo, keys, failKeys,  (int)(System.currentTimeMillis() / 1000) + 30) + failKeys);
	  		
	  		//System.out.println("after batch set, sub keys: " + java.util.Arrays.toString(client.getSubKeys(objInfo)));
	  		
	  		System.out.println("batch delete: " + client.batchDelete(objInfo, keys, failKeys) + failKeys);
	  		
	  		System.out.println("stat: " + client.stat(1));
	  		
	  		System.out.println("after batch delete, sub keys: " + Arrays.toString(client.getSubKeys(objInfo)));
  		}
  		finally
  		{
  			client.close();
  		}
  	}
  	catch(Exception ex)
  	{
  		ex.printStackTrace();
  	}
  }
}
