/**
* Copyright (C) 2008 Happy Fish / YuQing
*
* FastDHT Java Client may be copied only under the terms of the GNU Lesser
* General Public License (LGPL).
* Please visit the FastDHT Home Page http://fastdht.csource.org/ for more detail.
**/

package org.csource.fastdht;

import java.net.InetSocketAddress;
import java.net.Socket;

/**
* FastDHT server info
* @author Happy Fish / YuQing
* @version Version 1.00
*/
public class ServerInfo
{
	protected InetSocketAddress address;
	protected Socket sock = null;
	
	public ServerInfo(InetSocketAddress address)
	{
		this.address = address;
	}
	
	public InetSocketAddress getAddress()
	{
		return this.address;
	}
	
	public Socket getSocket()
	{
		return this.sock;
	}
}
