/**
 * Copyright (C) 2008 Happy Fish / YuQing
 *
 * FastDHT Java Client may be copied only under the terms of the GNU Lesser
 * General Public License (LGPL).
 * Please visit the FastDHT Home Page http://fastdht.csource.org/ for more detail.
 **/

package org.csource.fastdht;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.csource.common.IniFileReader;
import org.csource.common.MyException;

/**
 * Global variables
 *
 * @author Happy Fish / YuQing
 * @version Version 1.00
 */
public class ClientGlobal {
    public static int g_network_timeout; //millisecond
    public static String g_charset;      //String charset
    public static ServerGroup g_server_group; //group info

    public static final int DEFAULT_NETWORK_TIMEOUT = 30; //second

    /**
     * load global variables
     *
     * @param conf_filename config filename
     */
    public static void init(String conf_filename) throws FileNotFoundException, IOException, MyException {
        IniFileReader iniReader;

        iniReader = new IniFileReader(conf_filename);

        g_network_timeout = iniReader.getIntValue("network_timeout", DEFAULT_NETWORK_TIMEOUT);
        if (g_network_timeout < 0) {
            g_network_timeout = DEFAULT_NETWORK_TIMEOUT;
        }
        g_network_timeout *= 1000; //millisecond

        g_charset = iniReader.getStrValue("charset");
        if (g_charset == null || g_charset.length() == 0) {
            g_charset = "ISO8859-1";
        }

        g_server_group = ServerGroup.loadFromFile(iniReader);
    }

    private ClientGlobal() {
    }
}
