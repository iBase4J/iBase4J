/**
 * Copyright (C) 2008 Happy Fish / YuQing
 *
 * FastDHT Java Client may be copied only under the terms of the GNU Lesser
 * General Public License (LGPL).
 * Please visit the FastDHT Home Page http://fastdht.csource.org/ for more detail.
 */

package org.csource.fastdht;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;

import org.csource.common.MyException;

/**
 * FastDHT client
 *
 * @author Happy Fish / YuQing
 * @version Version 1.05
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class FastDHTClient {
    protected ServerGroup serverGroup;
    protected byte status;

    /**
     * @param serverGroup group info
     */
    public FastDHTClient(ServerGroup serverGroup) {
        this.serverGroup = serverGroup;
    }

    /**
     * @param bMultiThread if use in multi-thread
     */
    public FastDHTClient(boolean bMultiThread) {
        if (bMultiThread) {
            this.serverGroup = (ServerGroup) ClientGlobal.g_server_group.clone();
        } else {
            this.serverGroup = ClientGlobal.g_server_group;
        }
    }

    public void close() {
        this.serverGroup.closeAll();
    }

    /**
     * @return the error code of last call
     */
    public byte getErrorCode() {
        return this.status;
    }

    /**
     * set value of key
     *
     * @param keyInfo the key to set
     * @param value   value of the key
     * @return 0 for success, != 0(errno) for fail
     */
    public int set(KeyInfo keyInfo, String value) throws UnsupportedEncodingException, MyException {
        return this.set(keyInfo, value.getBytes(ClientGlobal.g_charset), ProtoCommon.FDHT_EXPIRES_NEVER);
    }

    /**
     * set value of key
     *
     * @param keyInfo the key to set
     * @param value   value of the key
     * @return 0 for success, != 0(errno) for fail
     */
    public int set(KeyInfo keyInfo, byte[] value) throws UnsupportedEncodingException, MyException {
        return this.set(keyInfo, value, ProtoCommon.FDHT_EXPIRES_NEVER);
    }

    /**
     * set value of key
     *
     * @param keyInfo the key to set
     * @param value   value of the key
     * @param expires expire timestamp, ProtoCommon.FDHT_EXPIRES_NEVER for never expired
     * @return 0 for success, != 0(errno) for fail
     */
    public int set(KeyInfo keyInfo, String value, int expires) throws UnsupportedEncodingException, MyException {
        return this.set(keyInfo, value.getBytes(ClientGlobal.g_charset), expires);
    }

    /**
     * set value of key
     *
     * @param keyInfo the key to set
     * @param value   value of the key
     * @param expires expire timestamp, ProtoCommon.FDHT_EXPIRES_NEVER for never expired
     * @return 0 for success, != 0(errno) for fail
     */
    public int set(KeyInfo keyInfo, byte[] value, int expires) throws UnsupportedEncodingException, MyException {
        ServerInfo server;
        ProtoCommon.PkgHeader header;
        byte[] bsPackage;
        int offset;

        header = new ProtoCommon.PkgHeader();
        header.key_hash_code = keyInfo.getHashCode();
        server = this.serverGroup.getServer(header.key_hash_code);
        if (server == null) {
            this.status = 2;
            return this.status;
        }

        try {
            header.body_len = keyInfo.getPackLength() + 4 + value.length;
            header.cmd = ProtoCommon.FDHT_PROTO_CMD_SET;
            header.keep_alive = (byte) (this.serverGroup.keep_alive ? 1 : 0);
            header.timestamp = (int) (System.currentTimeMillis() / 1000);
            header.expires = expires;

            bsPackage = new byte[ProtoCommon.FDHT_PROTO_PKG_HEADER_SIZE + header.body_len];
            ProtoCommon.packHeader(header, bsPackage);
            offset = keyInfo.pack(bsPackage, ProtoCommon.FDHT_PROTO_PKG_HEADER_SIZE);

            ProtoCommon.int2buff(value.length, bsPackage, offset);
            offset += 4;
            System.arraycopy(value, 0, bsPackage, offset, value.length);

            OutputStream out = server.sock.getOutputStream();
            out.write(bsPackage);

            ProtoCommon.PkgInfo pkgInfo = ProtoCommon.recvPackage(server.sock.getInputStream(),
                    ProtoCommon.FDHT_PROTO_CMD_RESP, 0);
            this.status = pkgInfo.header.status;
            return this.status;
        } catch (IOException ex) {
            System.err.println("server " + server.address.getAddress().getHostAddress() + ":" + server.address.getPort()
                    + " IOException, error msg: " + ex.getMessage());

            this.serverGroup.forceClose(server);
            this.status = 5;
            return this.status;
        } finally {
            this.serverGroup.closeServer(server);
        }
    }

    /**
     * delete key
     *
     * @param keyInfo the key to delete
     * @return 0 for success, != 0(status) for fail
     */
    public int delete(KeyInfo keyInfo) throws MyException {
        ServerInfo server;
        ProtoCommon.PkgHeader header;
        byte[] bsPackage;

        header = new ProtoCommon.PkgHeader();
        header.key_hash_code = keyInfo.getHashCode();
        server = this.serverGroup.getServer(header.key_hash_code);
        if (server == null) {
            this.status = 2;
            return this.status;
        }

        try {
            header.body_len = keyInfo.getPackLength();
            header.cmd = ProtoCommon.FDHT_PROTO_CMD_DEL;
            header.keep_alive = (byte) (this.serverGroup.keep_alive ? 1 : 0);
            header.timestamp = (int) (System.currentTimeMillis() / 1000);
            header.expires = 0;

            bsPackage = new byte[ProtoCommon.FDHT_PROTO_PKG_HEADER_SIZE + header.body_len];
            ProtoCommon.packHeader(header, bsPackage);
            keyInfo.pack(bsPackage, ProtoCommon.FDHT_PROTO_PKG_HEADER_SIZE);

            OutputStream out = server.sock.getOutputStream();
            out.write(bsPackage);

            ProtoCommon.PkgInfo pkgInfo = ProtoCommon.recvPackage(server.sock.getInputStream(),
                    ProtoCommon.FDHT_PROTO_CMD_RESP, 0);
            this.status = pkgInfo.header.status;
            return this.status;
        } catch (IOException ex) {
            System.err.println("server " + server.address.getAddress().getHostAddress() + ":" + server.address.getPort()
                    + " IOException, error msg: " + ex.getMessage());

            this.serverGroup.forceClose(server);
            this.status = 5;
            return this.status;
        } finally {
            this.serverGroup.closeServer(server);
        }
    }

    /**
     * retrieve value of key
     *
     * @param keyInfo the key to get
     * @return none null for success, null for fail
     */
    public String get(KeyInfo keyInfo) throws UnsupportedEncodingException, MyException {
        return this.get(keyInfo, ProtoCommon.FDHT_EXPIRES_NONE);
    }

    /**
     * retrieve value of key
     *
     * @param keyInfo the key to get
     * @param expires expire timestamp, ProtoCommon.FDHT_EXPIRES_NONE for not change the expired time
     * @return none null for success, null for fail
     */
    public String get(KeyInfo keyInfo, int expires) throws UnsupportedEncodingException, MyException {
        byte[] bs;
        bs = this.getBytes(keyInfo, expires);
        if (bs == null) {
            return null;
        }

        return new String(bs, ClientGlobal.g_charset);
    }

    /**
     * retrieve value of key
     *
     * @param keyInfo the key to get
     * @return none null for success, null for fail
     */
    public byte[] getBytes(KeyInfo keyInfo) throws UnsupportedEncodingException, MyException {
        return this.getBytes(keyInfo, ProtoCommon.FDHT_EXPIRES_NONE);
    }

    /**
     * retrieve value of key
     *
     * @param keyInfo the key to get
     * @param expires expire timestamp, ProtoCommon.FDHT_EXPIRES_NONE for not change the expired time
     * @return none null for success, null for fail
     */
    public byte[] getBytes(KeyInfo keyInfo, int expires) throws MyException {
        ServerInfo server;
        ProtoCommon.PkgHeader header;
        byte[] bsPackage;
        int value_len;

        header = new ProtoCommon.PkgHeader();
        header.key_hash_code = keyInfo.getHashCode();
        server = this.serverGroup.getServer(header.key_hash_code);
        if (server == null) {
            this.status = 2;
            return null;
        }

        try {
            header.body_len = keyInfo.getPackLength();
            header.cmd = ProtoCommon.FDHT_PROTO_CMD_GET;
            header.keep_alive = (byte) (this.serverGroup.keep_alive ? 1 : 0);
            header.timestamp = (int) (System.currentTimeMillis() / 1000);
            header.expires = expires;

            bsPackage = new byte[ProtoCommon.FDHT_PROTO_PKG_HEADER_SIZE + header.body_len];
            ProtoCommon.packHeader(header, bsPackage);
            keyInfo.pack(bsPackage, ProtoCommon.FDHT_PROTO_PKG_HEADER_SIZE);

            OutputStream out = server.sock.getOutputStream();
            out.write(bsPackage);

            ProtoCommon.PkgInfo pkgInfo = ProtoCommon.recvPackage(server.sock.getInputStream(),
                    ProtoCommon.FDHT_PROTO_CMD_RESP, -1);
            this.status = pkgInfo.header.status;
            if (this.status != 0) {
                return null;
            }

            if (pkgInfo.body.length < 4) {
                this.serverGroup.forceClose(server);
                this.status = 22;
                return null;
            }

            value_len = ProtoCommon.buff2int(pkgInfo.body, 0);
            if (value_len + 4 != pkgInfo.body.length) {
                this.serverGroup.forceClose(server);
                this.status = 22;
                return null;
            }

            byte[] result = new byte[value_len];
            System.arraycopy(pkgInfo.body, 4, result, 0, value_len);
            return result;
        } catch (IOException ex) {
            System.err.println("server " + server.address.getAddress().getHostAddress() + ":" + server.address.getPort()
                    + " IOException, error msg: " + ex.getMessage());

            this.serverGroup.forceClose(server);
            this.status = 5;
            return null;
        } finally {
            this.serverGroup.closeServer(server);
        }
    }

    public Long inc(KeyInfo keyInfo, int increment) throws MyException {
        return this.inc(keyInfo, increment, ProtoCommon.FDHT_EXPIRES_NEVER);
    }

    /**
     * retrieve value of key
     *
     * @param keyInfo   the key to get
     * @param increment increment value
     * @param expires   expire timestamp, ProtoCommon.FDHT_EXPIRES_NEVER for never expired
     * @return none null for success, null for fail
     */
    public Long inc(KeyInfo keyInfo, int increment, int expires) throws MyException {
        ServerInfo server;
        ProtoCommon.PkgHeader header;
        byte[] bsPackage;
        int offset;
        int value_len;

        header = new ProtoCommon.PkgHeader();
        header.key_hash_code = keyInfo.getHashCode();
        server = this.serverGroup.getServer(header.key_hash_code);
        if (server == null) {
            this.status = 2;
            return null;
        }

        try {
            header.body_len = keyInfo.getPackLength() + 4;
            header.cmd = ProtoCommon.FDHT_PROTO_CMD_INC;
            header.keep_alive = (byte) (this.serverGroup.keep_alive ? 1 : 0);
            header.timestamp = (int) (System.currentTimeMillis() / 1000);
            header.expires = expires;

            bsPackage = new byte[ProtoCommon.FDHT_PROTO_PKG_HEADER_SIZE + header.body_len];
            ProtoCommon.packHeader(header, bsPackage);
            offset = keyInfo.pack(bsPackage, ProtoCommon.FDHT_PROTO_PKG_HEADER_SIZE);
            ProtoCommon.int2buff(increment, bsPackage, offset);

            OutputStream out = server.sock.getOutputStream();
            out.write(bsPackage);

            ProtoCommon.PkgInfo pkgInfo = ProtoCommon.recvPackage(server.sock.getInputStream(),
                    ProtoCommon.FDHT_PROTO_CMD_RESP, -1);
            this.status = pkgInfo.header.status;
            if (this.status != 0) {
                return null;
            }

            if (pkgInfo.body.length < 4) {
                this.serverGroup.forceClose(server);
                this.status = 22;
                return null;
            }

            value_len = ProtoCommon.buff2int(pkgInfo.body, 0);
            if (value_len + 4 != pkgInfo.body.length) {
                this.serverGroup.forceClose(server);
                this.status = 22;
                return null;
            }

            String value = new String(pkgInfo.body, 4, value_len);
            return new Long(value);
        } catch (IOException ex) {
            System.err.println("server " + server.address.getAddress().getHostAddress() + ":" + server.address.getPort()
                    + " IOException, error msg: " + ex.getMessage());
            this.serverGroup.forceClose(server);
            this.status = 5;
            return null;
        } finally {
            this.serverGroup.closeServer(server);
        }
    }

	private int parseBatchUpdate(int src_key_count, ServerInfo server, ProtoCommon.PkgInfo pkgInfo, Map failKeys) throws UnsupportedEncodingException {
        int offset;
        int key_count;
        int success_count;
        String key;
        int key_len;
        byte status;

        this.status = pkgInfo.header.status;
        if (this.status != 0) {
            return -1 * this.status;
        }

        if (pkgInfo.body.length < 8 + 5 * src_key_count) {
            System.err.println("server " + server.address.getAddress().getHostAddress() + ":" + server.address.getPort()
                    + " body length: " + pkgInfo.body.length + " < " + (8 + 5 * src_key_count));

            this.serverGroup.forceClose(server);
            this.status = 22;
            return -1 * this.status;
        }

        key_count = ProtoCommon.buff2int(pkgInfo.body, 0);
        if (key_count != src_key_count) {
            System.err.println("server " + server.address.getAddress().getHostAddress() + ":" + server.address.getPort()
                    + " key count: " + key_count + " != " + src_key_count);

            this.serverGroup.forceClose(server);
            this.status = 22;
            return -1 * this.status;
        }

        offset = 4;
        success_count = ProtoCommon.buff2int(pkgInfo.body, offset);
        offset += 4;
        failKeys.clear();
        if (success_count == key_count) {
            return success_count;
        }

        for (int i = 0; i < key_count; i++) {
            key_len = ProtoCommon.buff2int(pkgInfo.body, offset);
            offset += 4;
            key = new String(pkgInfo.body, offset, key_len, ClientGlobal.g_charset);
            status = pkgInfo.body[offset + key_len];
            offset += key_len + 1;
            if (status != 0) {
                failKeys.put(key, new Integer(status));
            }
        }

        return success_count;
    }

    /**
     * set values of multi keys
     *
     * @param objInfo   object info, including namespace and object id
     * @param keyValues key value pair, key and value's type is String
     * @param failKeys  fail key, key 's type is String and value's type is Integer
     * @return success set key count, < 0 for all keys fail
     */
    public int batchSet(ObjectInfo objInfo, Map keyValues, Map failKeys) throws MyException {
        return this.batchSet(objInfo, keyValues, failKeys, ProtoCommon.FDHT_EXPIRES_NEVER);
    }

    /**
     * set values of multi keys
     *
     * @param objInfo   object info, including namespace and object id
     * @param keyValues key value pair, key and value's type is String
     * @param failKeys  fail key, key 's type is String and value's type is Integer
     * @param expires   expire timestamp, ProtoCommon.FDHT_EXPIRES_NEVER for never expired
     * @return success set key count, < 0 for all keys fail
     */
    public int batchSet(ObjectInfo objInfo, Map keyValues, Map failKeys, int expires) throws MyException {
        ServerInfo server;
        ProtoCommon.PkgHeader header;
        byte[] bsPackage;
        int offset;
        Iterator it;
        Map.Entry entry;
        String key;
        String value;
        byte[] bsKey;
        byte[] bsValue;

        header = new ProtoCommon.PkgHeader();
        header.key_hash_code = objInfo.getHashCode();
        server = this.serverGroup.getServer(header.key_hash_code);
        if (server == null) {
            this.status = 2;
            return -1 * this.status;
        }

        try {
            header.body_len = objInfo.getPackLength() + 4;
            header.cmd = ProtoCommon.FDHT_PROTO_CMD_BATCH_SET;
            header.keep_alive = (byte) (this.serverGroup.keep_alive ? 1 : 0);
            header.timestamp = (int) (System.currentTimeMillis() / 1000);
            header.expires = expires;

            it = keyValues.entrySet().iterator();
            while (it.hasNext()) {
                entry = (Map.Entry) it.next();
                key = (String) entry.getKey();
                value = (String) entry.getValue();

                header.body_len += 8 + key.getBytes(ClientGlobal.g_charset).length
                        + value.getBytes(ClientGlobal.g_charset).length;
            }

            bsPackage = new byte[ProtoCommon.FDHT_PROTO_PKG_HEADER_SIZE + header.body_len];
            ProtoCommon.packHeader(header, bsPackage);
            offset = objInfo.pack(bsPackage, ProtoCommon.FDHT_PROTO_PKG_HEADER_SIZE);
            ProtoCommon.int2buff(keyValues.size(), bsPackage, offset);
            offset += 4;

            it = keyValues.entrySet().iterator();
            while (it.hasNext()) {
                entry = (Map.Entry) it.next();
                key = (String) entry.getKey();
                value = (String) entry.getValue();

                bsKey = key.getBytes(ClientGlobal.g_charset);
                bsValue = value.getBytes(ClientGlobal.g_charset);

                ProtoCommon.int2buff(bsKey.length, bsPackage, offset);
                offset += 4;
                System.arraycopy(bsKey, 0, bsPackage, offset, bsKey.length);
                offset += bsKey.length;

                ProtoCommon.int2buff(bsValue.length, bsPackage, offset);
                offset += 4;
                System.arraycopy(bsValue, 0, bsPackage, offset, bsValue.length);
                offset += bsValue.length;
            }

            OutputStream out = server.sock.getOutputStream();
            out.write(bsPackage);

            ProtoCommon.PkgInfo pkgInfo = ProtoCommon.recvPackage(server.sock.getInputStream(),
                    ProtoCommon.FDHT_PROTO_CMD_RESP, -1);
            return this.parseBatchUpdate(keyValues.size(), server, pkgInfo, failKeys);
        } catch (IOException ex) {
            System.err.println("server " + server.address.getAddress().getHostAddress() + ":" + server.address.getPort()
                    + " IOException, error msg: " + ex.getMessage());

            this.serverGroup.forceClose(server);
            this.status = 5;
            return -1 * this.status;
        } finally {
            this.serverGroup.closeServer(server);
        }
    }

    /**
     * delete multi keys
     *
     * @param objInfo  object info, including namespace and object id
     * @param keys     key array
     * @param failKeys fail key, key 's type is String and value's type is Integer
     * @return success set key count, < 0 for all keys fail
     */
    @SuppressWarnings("unused")
	public int batchDelete(ObjectInfo objInfo, String[] keys, Map failKeys) throws MyException {
        ServerInfo server;
        ProtoCommon.PkgHeader header;
        byte[] bsPackage;
        int offset;
        Iterator it;
        Map.Entry entry;
        String key;
        String value;
        byte[] bsKey;
        byte[] bsValue;
        int i;

        header = new ProtoCommon.PkgHeader();
        header.key_hash_code = objInfo.getHashCode();
        server = this.serverGroup.getServer(header.key_hash_code);
        if (server == null) {
            this.status = 2;
            return -1 * this.status;
        }

        try {
            header.body_len = objInfo.getPackLength() + 4;
            header.cmd = ProtoCommon.FDHT_PROTO_CMD_BATCH_DEL;
            header.keep_alive = (byte) (this.serverGroup.keep_alive ? 1 : 0);
            header.timestamp = (int) (System.currentTimeMillis() / 1000);
            header.expires = 0;

            for (i = 0; i < keys.length; i++) {
                header.body_len += 4 + keys[i].getBytes(ClientGlobal.g_charset).length;
            }

            bsPackage = new byte[ProtoCommon.FDHT_PROTO_PKG_HEADER_SIZE + header.body_len];
            ProtoCommon.packHeader(header, bsPackage);
            offset = objInfo.pack(bsPackage, ProtoCommon.FDHT_PROTO_PKG_HEADER_SIZE);
            ProtoCommon.int2buff(keys.length, bsPackage, offset);
            offset += 4;

            for (i = 0; i < keys.length; i++) {
                bsKey = keys[i].getBytes(ClientGlobal.g_charset);

                ProtoCommon.int2buff(bsKey.length, bsPackage, offset);
                offset += 4;
                System.arraycopy(bsKey, 0, bsPackage, offset, bsKey.length);
                offset += bsKey.length;
            }

            OutputStream out = server.sock.getOutputStream();
            out.write(bsPackage);

            ProtoCommon.PkgInfo pkgInfo = ProtoCommon.recvPackage(server.sock.getInputStream(),
                    ProtoCommon.FDHT_PROTO_CMD_RESP, -1);
            return this.parseBatchUpdate(keys.length, server, pkgInfo, failKeys);
        } catch (IOException ex) {
            System.err.println("server " + server.address.getAddress().getHostAddress() + ":" + server.address.getPort()
                    + " IOException, error msg: " + ex.getMessage());

            this.serverGroup.forceClose(server);
            this.status = 5;
            return -1 * this.status;
        } finally {
            this.serverGroup.closeServer(server);
        }
    }

    /**
     * get multi keys
     *
     * @param objInfo  object info, including namespace and object id
     * @param keys     key array
     * @param failKeys fail key, key 's type is String and value's type is Integer
     * @return none null for success, null for all keys fail
     */
    public Hashtable batchGet(ObjectInfo objInfo, String[] keys, Map failKeys) throws MyException {
        return this.batchGet(objInfo, keys, failKeys, ProtoCommon.FDHT_EXPIRES_NONE);
    }

    /**
     * get multi keys
     *
     * @param objInfo  object info, including namespace and object id
     * @param keys     key array
     * @param failKeys fail key, key 's type is String and value's type is Integer
     * @param expires  expire timestamp, ProtoCommon.FDHT_EXPIRES_NONE for not change the expired time
     * @return none null for success, null for all keys fail
     */
    @SuppressWarnings("unused")
	public Hashtable batchGet(ObjectInfo objInfo, String[] keys, Map failKeys, int expires) throws MyException {
        ServerInfo server;
        ProtoCommon.PkgHeader header;
        byte[] bsPackage;
        int offset;
        Iterator it;
        Map.Entry entry;
        String key;
        String value;
        byte[] bsKey;
        byte[] bsValue;
        int i;
        Hashtable keyValues;
        int key_count;
        int key_len;
        int value_len;
        byte status;

        header = new ProtoCommon.PkgHeader();
        header.key_hash_code = objInfo.getHashCode();
        server = this.serverGroup.getServer(header.key_hash_code);
        if (server == null) {
            this.status = 2;
            return null;
        }

        try {
            header.body_len = objInfo.getPackLength() + 4;
            header.cmd = ProtoCommon.FDHT_PROTO_CMD_BATCH_GET;
            header.keep_alive = (byte) (this.serverGroup.keep_alive ? 1 : 0);
            header.timestamp = (int) (System.currentTimeMillis() / 1000);
            header.expires = expires;

            for (i = 0; i < keys.length; i++) {
                header.body_len += 4 + keys[i].getBytes(ClientGlobal.g_charset).length;
            }

            bsPackage = new byte[ProtoCommon.FDHT_PROTO_PKG_HEADER_SIZE + header.body_len];
            ProtoCommon.packHeader(header, bsPackage);
            offset = objInfo.pack(bsPackage, ProtoCommon.FDHT_PROTO_PKG_HEADER_SIZE);
            ProtoCommon.int2buff(keys.length, bsPackage, offset);
            offset += 4;

            for (i = 0; i < keys.length; i++) {
                bsKey = keys[i].getBytes(ClientGlobal.g_charset);

                ProtoCommon.int2buff(bsKey.length, bsPackage, offset);
                offset += 4;
                System.arraycopy(bsKey, 0, bsPackage, offset, bsKey.length);
                offset += bsKey.length;
            }

            OutputStream out = server.sock.getOutputStream();
            out.write(bsPackage);

            ProtoCommon.PkgInfo pkgInfo = ProtoCommon.recvPackage(server.sock.getInputStream(),
                    ProtoCommon.FDHT_PROTO_CMD_RESP, -1);

            this.status = pkgInfo.header.status;
            if (this.status != 0) {
                return null;
            }

            if (pkgInfo.body.length < 8 + 5 * keys.length) {
                System.err.println("server " + server.address.getAddress().getHostAddress() + ":" + server.address.getPort()
                        + " body length: " + pkgInfo.body.length + " < " + (8 + 5 * keys.length));

                this.serverGroup.forceClose(server);
                this.status = 22;
                return null;
            }

            key_count = ProtoCommon.buff2int(pkgInfo.body, 0);
            if (key_count != keys.length) {
                System.err.println("server " + server.address.getAddress().getHostAddress() + ":" + server.address.getPort()
                        + " key count: " + key_count + " != " + keys.length);

                this.serverGroup.forceClose(server);
                this.status = 22;
                return null;
            }

            offset = 8;
            failKeys.clear();
            keyValues = new Hashtable(2 * keys.length, (float) 0.50);
            for (i = 0; i < key_count; i++) {
                key_len = ProtoCommon.buff2int(pkgInfo.body, offset);
                offset += 4;
                key = new String(pkgInfo.body, offset, key_len, ClientGlobal.g_charset);
                status = pkgInfo.body[offset + key_len];
                offset += key_len + 1;
                if (status == 0) {
                    value_len = ProtoCommon.buff2int(pkgInfo.body, offset);
                    offset += 4;
                    value = new String(pkgInfo.body, offset, value_len, ClientGlobal.g_charset);
                    keyValues.put(key, value);
                    offset += value_len;
                } else {
                    failKeys.put(key, new Integer(status));
                }
            }

            return keyValues;
        } catch (IOException ex) {
            System.err.println("server " + server.address.getAddress().getHostAddress() + ":" + server.address.getPort()
                    + " IOException, error msg: " + ex.getMessage());

            this.serverGroup.forceClose(server);
            this.status = 5;
            return null;
        } finally {
            this.serverGroup.closeServer(server);
        }
    }

    /**
     * get stat hashtable
     *
     * @param server_index the server index
     * @return stat Hashtable for success, null for fail
     */
    @SuppressWarnings("unused")
	public Hashtable stat(int server_index) throws MyException {
        ServerInfo server;
        ProtoCommon.PkgHeader header;
        int offset;
        Hashtable keyValues;

        ServerInfo[] servers = this.serverGroup.getServers();
        server = servers[server_index];
        if (!this.serverGroup.connectServer(server)) {
            this.status = 2;
            return null;
        }

        try {
            header = new ProtoCommon.PkgHeader();
            header.key_hash_code = 0;
            header.body_len = 0;
            header.cmd = ProtoCommon.FDHT_PROTO_CMD_STAT;
            header.keep_alive = (byte) (this.serverGroup.keep_alive ? 1 : 0);
            header.timestamp = (int) (System.currentTimeMillis() / 1000);
            header.expires = 0;

            byte[] bsPackage = new byte[ProtoCommon.FDHT_PROTO_PKG_HEADER_SIZE];
            ProtoCommon.packHeader(header, bsPackage);

            OutputStream out = server.sock.getOutputStream();
            out.write(bsPackage);

            ProtoCommon.PkgInfo pkgInfo = ProtoCommon.recvPackage(server.sock.getInputStream(),
                    ProtoCommon.FDHT_PROTO_CMD_RESP, -1);

            this.status = pkgInfo.header.status;
            if (this.status != 0) {
                return null;
            }

            String szBody = new String(pkgInfo.body, ClientGlobal.g_charset);
            String[] rows = szBody.split("\n");
            String[] cols;
            keyValues = new Hashtable(rows.length, (float) 0.50);
            for (int i = 0; i < rows.length; i++) {
                cols = rows[i].split("=");
                keyValues.put(cols[0], cols[1]);
            }

            return keyValues;
        } catch (IOException ex) {
            System.err.println("server " + server.address.getAddress().getHostAddress() + ":" + server.address.getPort()
                    + " IOException, error msg: " + ex.getMessage());

            this.serverGroup.forceClose(server);
            this.status = 5;
            return null;
        } finally {
            this.serverGroup.closeServer(server);
        }
    }

    /**
     * get object's sub keys
     *
     * @param objInfo object info, including namespace and object id
     * @return string array for success, null for fail
     */
    @SuppressWarnings("unused")
	public String[] getSubKeys(ObjectInfo objInfo) throws UnsupportedEncodingException, MyException {
        ServerInfo server;
        ProtoCommon.PkgHeader header;
        byte[] bsPackage;
        byte status;

        header = new ProtoCommon.PkgHeader();
        header.key_hash_code = objInfo.getHashCode();
        server = this.serverGroup.getServer(header.key_hash_code);
        if (server == null) {
            this.status = 2;
            return null;
        }

        try {
            header.body_len = objInfo.getPackLength();
            header.cmd = ProtoCommon.FDHT_PROTO_CMD_GET_SUB_KEYS;
            header.keep_alive = (byte) (this.serverGroup.keep_alive ? 1 : 0);
            header.timestamp = 0;
            header.expires = 0;

            bsPackage = new byte[ProtoCommon.FDHT_PROTO_PKG_HEADER_SIZE + header.body_len];
            ProtoCommon.packHeader(header, bsPackage);
            objInfo.pack(bsPackage, ProtoCommon.FDHT_PROTO_PKG_HEADER_SIZE);

            OutputStream out = server.sock.getOutputStream();
            out.write(bsPackage);

            ProtoCommon.PkgInfo pkgInfo = ProtoCommon.recvPackage(server.sock.getInputStream(),
                    ProtoCommon.FDHT_PROTO_CMD_RESP, -1);

            this.status = pkgInfo.header.status;
            if (this.status != 0) {
                return null;
            }

            String szSubKeys = new String(pkgInfo.body, ClientGlobal.g_charset);
            return szSubKeys.split(ProtoCommon.FDHT_KEY_LIST_SEP_STR);
        } catch (IOException ex) {
            System.err.println("server " + server.address.getAddress().getHostAddress() + ":" + server.address.getPort()
                    + " IOException, error msg: " + ex.getMessage());

            this.serverGroup.forceClose(server);
            this.status = 5;
            return null;
        } finally {
            this.serverGroup.closeServer(server);
        }
    }
}
