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
* client test
* @author Happy Fish / YuQing
* @version Version 1.03
*/
public class Test
{
	private Test()
	{
	}
	
	/**
	* entry point
	* @param args comand arguments
	*     <ul><li>args[0]: config filename</li></ul>
	*/
  @SuppressWarnings("rawtypes")
public static void main(String args[])
  {
  	if (args.length < 1)
  	{
  		System.out.println("Usage: java org.csource.fastdht.Test <config filename>");
  		return;
  	}

  	System.out.println("java.version=" + System.getProperty("java.version"));
  	    
  	String conf_filename = args[0];
  	try
  	{
  		ClientGlobal.init(conf_filename);
  		FastDHTClient client;
  		KeyInfo keyInfo;
  		Hashtable stats;
  		
  		System.out.println("network_timeout=" + ClientGlobal.g_network_timeout + "ms");
  		System.out.println("charset=" + ClientGlobal.g_charset);
  		
  		ClientGlobal.g_server_group.print();
  		
  		client = new FastDHTClient(false);
  		try
  		{
	  		keyInfo = new KeyInfo("bbs", "test", "username");
	  		
	  		System.out.println("set: " + client.set(keyInfo, "12345678901234"));
	  		System.out.println("get: " + client.get(keyInfo));
	  		System.out.println("inc: " + client.inc(keyInfo, 100));
	  		//System.out.println("delete: " + client.delete(keyInfo));
	  		
	  		for (int i=0; i<ClientGlobal.g_server_group.servers.length; i++)
	  		{
	  			stats = client.stat(i);
	  			if (stats == null)
	  			{
	  				continue;
	  			}
	  			
	  			System.out.println("server=" + ClientGlobal.g_server_group.servers[i].address.getAddress().getHostAddress()
	  												 + ":" + ClientGlobal.g_server_group.servers[i].address.getPort());
	  			System.out.println(stats.toString());
	  		}
	  		
	  		ObjectInfo objInfo = new ObjectInfo(keyInfo.getNamespace(), keyInfo.getObjectId());
	  		System.out.println("sub keys: " + Arrays.toString(client.getSubKeys(objInfo)));
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
