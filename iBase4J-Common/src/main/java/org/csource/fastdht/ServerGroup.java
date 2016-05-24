/**
* Copyright (C) 2008 Happy Fish / YuQing
*
* FastDHT Java Client may be copied only under the terms of the GNU Lesser
* General Public License (LGPL).
* Please visit the FastDHT Home Page http://fastdht.csource.org/ for more detail.
**/

package org.csource.fastdht;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

import org.csource.common.IniFileReader;
import org.csource.common.MyException;

/**
* Global variables
* @author Happy Fish / YuQing
* @version Version 1.02
*/
@SuppressWarnings({"unchecked","rawtypes"})
public class ServerGroup
{
	public static class ServerComparator implements java.util.Comparator
	{
		public int compare(Object o1, Object o2)
		{
			ServerInfo s1 = (ServerInfo)o1;
			ServerInfo s2 = (ServerInfo)o2;
			
			int compResult = s1.address.getAddress().getHostAddress().compareTo(s2.address.getAddress().getHostAddress());
			if (compResult != 0)
			{
				return compResult;
			}
			
			return s1.address.getPort() - s2.address.getPort();
		}
		
		public boolean equals(Object obj)
		{
			return this.compare(this, obj) == 0;
		}
	}
	
	protected ServerInfo[] servers;  //distinct servers
	protected ServerInfo[][] groups; //group info array
	protected boolean keep_alive;    //if keep persistent connection

	protected ServerGroup()
	{
		
	}
	
	public ServerGroup(ServerInfo[] servers, ServerInfo[][] groups, boolean keep_alive)
	{
		this.servers = servers;
		this.groups = groups;
		this.keep_alive = keep_alive;
	}
	
	public void setKeepAlive(boolean keep_alive)
	{
		this.keep_alive = keep_alive;
	}
	
	public boolean getKeepAlive()
	{
		return this.keep_alive;
	}
	
	public int getGroupCount()
	{
		return this.groups.length;
	}
	
	public ServerInfo[][] getGroups()
	{
		return this.groups;
	}
	
	public int getServerCount()
	{
		return this.servers.length;
	}
	
	public ServerInfo[] getServers()
	{
		return this.servers;
	}
	
	public Object clone()
	{
		int i;
		int k;
		int index;
		ServerGroup serverGroup;
		ServerInfo[] group_servers;
		ServerComparator compObj = new ServerComparator();
		
		serverGroup = new ServerGroup();
		
		serverGroup.servers = new ServerInfo[this.servers.length];
		for (i=0; i<this.servers.length; i++)
		{
			serverGroup.servers[i] = new ServerInfo(this.servers[i].address);
		}
		
		serverGroup.groups = new ServerInfo[this.groups.length][];
		for (i=0; i<this.groups.length; i++)
		{
			group_servers = new ServerInfo[this.groups[i].length];
			for (k=0; k<group_servers.length; k++)
			{
				index = java.util.Arrays.binarySearch(serverGroup.servers, this.groups[i][k], compObj);
				group_servers[k] = serverGroup.servers[index];
			}
			serverGroup.groups[i] = group_servers;
		}
		
		serverGroup.keep_alive = this.keep_alive;
		return serverGroup;
	}
	
/**
* load group info from config file
* @param iniReader config filename reader
* @return group info
*/
	public static ServerGroup loadFromFile(IniFileReader iniReader) throws MyException
	{
			ServerGroup serverGroup;
			ServerInfo serverInfo;
			ServerInfo[] servers;
			ServerComparator compObj = new ServerComparator();
			int keep_alive;
			int group_count;
			int server_index;
  		String[] szServers;
			String[] parts;
			
			serverGroup = new ServerGroup();
  		keep_alive = iniReader.getIntValue("keep_alive", 0);
  		serverGroup.keep_alive = keep_alive != 0;
  		
  		group_count = iniReader.getIntValue("group_count", 0);
  		if (group_count <= 0)
  		{
  			throw new MyException("Invalid group_count: " + group_count);
  		}
  		
  		serverGroup.groups = new ServerInfo[group_count][];
  		serverGroup.servers = new ServerInfo[0];
  		for (int i=0; i<group_count; i++)
  		{
  			szServers = iniReader.getValues("group" + i);
	  		if (szServers == null || szServers.length == 0)
	  		{
	  			throw new MyException("item \"group" + i + "\" in " + iniReader.getConfFilename() + " not found");
	  		}
	  		
	  		serverGroup.groups[i] = new ServerInfo[0];
	  		for (int k=0; k<szServers.length; k++)
	  		{
	  			parts = szServers[k].split("\\:", 2);
	  			if (parts.length != 2)
	  			{
	  				throw new MyException("the value of item \"group" + i + "\" is invalid, the correct format is host:port");
	  			}
	  			
	  			serverInfo = new ServerInfo(new InetSocketAddress(parts[0].trim(), Integer.parseInt(parts[1].trim())));
	  			server_index = java.util.Arrays.binarySearch(serverGroup.servers, serverInfo, compObj);
	  			if (server_index < 0)
	  			{
	  				servers = new ServerInfo[serverGroup.servers.length + 1];
	  				if (serverGroup.servers.length > 0)
	  				{
	  					System.arraycopy(serverGroup.servers, 0, servers, 0, serverGroup.servers.length);
	  				}
	  				servers[serverGroup.servers.length] = serverInfo;
	  				java.util.Arrays.sort(servers, compObj);
	  				serverGroup.servers = servers;
	  				server_index = java.util.Arrays.binarySearch(serverGroup.servers, serverInfo, compObj);
	  			}
	  			
	  			if (java.util.Arrays.binarySearch(serverGroup.groups[i], serverInfo, compObj) < 0)
	  			{
						servers = new ServerInfo[serverGroup.groups[i].length + 1];
	  				if (serverGroup.groups[i].length > 0)
	  				{
	  					System.arraycopy(serverGroup.groups[i], 0, servers, 0, serverGroup.groups[i].length);
	  				}
	  				servers[serverGroup.groups[i].length] = serverGroup.servers[server_index];
	  				java.util.Arrays.sort(servers, compObj);
	  				serverGroup.groups[i] = servers;
  				}
	  		}
  		}
  		
  		return serverGroup;
	}
	
/**
* connect to server
* @param server the server
* @return true for success, false for fail
*/
	@SuppressWarnings("resource")
	public boolean connectServer(ServerInfo server)
	{
			Socket sock;
    	if (server.sock != null)
    	{
    		return true;
    	}
    	
			try
			{
				sock = new Socket();
				sock.setReuseAddress(true);
				sock.setSoTimeout(ClientGlobal.g_network_timeout);
				sock.connect(server.address, ClientGlobal.g_network_timeout);
				server.sock = sock;
				return true;
			}
			catch(IOException ex)
			{
				System.err.println("connect to server " + server.address.getAddress().getHostAddress()
							 + ":" + server.address.getPort() + " fail, error info: " + ex.getMessage());
				return false;
			}
	}
	
/**
* get connected server
* @param key_hash_code the key hash code
* @return != null for success, null for fail
*/
	public ServerInfo getServer(int key_hash_code)
	{
		int group_id;
    int server_index;
    int server_count;
    int new_hash_code;
		int i;
		
		group_id = key_hash_code % this.getGroupCount();
		server_count = this.groups[group_id].length;
		
    new_hash_code = (key_hash_code << 16) | (key_hash_code >> 16);
    if (new_hash_code < 0)
    {
            new_hash_code &= 0x7FFFFFFF;
    }
    server_index = new_hash_code % server_count;
    
    for (i=server_index; i<server_count; i++)
    {
			if (this.connectServer(this.groups[group_id][i]))
			{
				return this.groups[group_id][i];
			}
    }
    
    for (i=0; i<server_index; i++)
    {
			if (this.connectServer(this.groups[group_id][i]))
			{
				return this.groups[group_id][i];
			}
    }
    
		return null;
	}
	
/**
* close server
* @param server the sever to close
*/
	public void closeServer(ServerInfo server)
	{
		if (server.sock == null || this.keep_alive)
		{
			return;
		}
		
		try
		{
			server.sock.close();
			server.sock = null;
		}
		catch(IOException ex)
		{
			System.err.println("close socket error: " + ex.getMessage());
		}
	}
	
/**
* force close server
* @param server the sever to close
*/
	public void forceClose(ServerInfo server)
	{
		if (server == null || server.sock == null)
		{
			return;
		}
		
		try
		{
			if (this.keep_alive)
			{
				try
				{
					ProtoCommon.quit(server.sock);
				}
				catch(IOException ex)
				{
					System.err.println("quit error: " + ex.getMessage());
				}
			}
			
			server.sock.close();
			server.sock = null;
		}
		catch(IOException ex)
		{
			System.err.println("close socket error: " + ex.getMessage());
		}
	}
	
/**
* close all servers
*/
	public void closeAll()
	{
		if (this.servers == null)
		{
			return;
		}
		
		for (int i=0; i<this.servers.length; i++)
		{
			this.forceClose(this.servers[i]);
		}
	}

	protected void finalize() throws Throwable
	{
		this.closeAll();
	}

/**
* print group info (for debug)
*/
	public void print()
	{
		int i;
		System.out.println("group count: " + groups.length + ", distinct server count: " + servers.length + ", keep_alive=" + this.keep_alive);
		for (i=0; i<servers.length; i++)
		{
			System.out.println("server" + (i + 1) + ". " + servers[i].address.getAddress().getHostAddress() + ":" + servers[i].address.getPort());
		}

		for (i=0; i<groups.length; i++)
		{
			System.out.println("group " + i + " server count: " + groups[i].length);
			for (int k=0; k<groups[i].length; k++)
			{
				System.out.println("server" + (k + 1) + ". " + groups[i][k].address.getAddress().getHostAddress() + ":" + groups[i][k].address.getPort());
			}
		}
	}
}
