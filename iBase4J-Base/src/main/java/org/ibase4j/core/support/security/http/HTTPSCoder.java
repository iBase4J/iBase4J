/**
 * 2011-01-11
 */
package org.ibase4j.core.support.security.http;

import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.SecureRandom;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

/**
 * HTTPS组件
 * 
 * @author ShenHuaJie
 * @version 1.0
 * @since 1.0
 */
public abstract class HTTPSCoder {
	/**
	 * 协议
	 */
	public static final String PROTOCOL = "TLS";

	/**
	 * 获得KeyStore
	 * 
	 * @param keyStorePath 密钥库路径
	 * @param password 密码
	 * @return KeyStore 密钥库
	 * @throws Exception
	 */
	private static KeyStore getKeyStore(String keyStorePath, String password) throws Exception {
		// 实例化密钥库
		KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
		// 获得密钥库文件流
		FileInputStream is = new FileInputStream(keyStorePath);
		// 加载密钥库
		ks.load(is, password.toCharArray());
		// 关闭密钥库文件流
		is.close();
		return ks;
	}

	/**
	 * 获得SSLSocektFactory
	 * 
	 * @param password 密码
	 * @param keyStorePath 密钥库路径
	 * @param trustStorePath 信任库路径
	 * @return SSLSocketFactory
	 * @throws Exception
	 */
	private static SSLSocketFactory getSSLSocketFactory(String password, String keyStorePath, String trustStorePath)
			throws Exception {
		// 实例化密钥库
		KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
		// 获得密钥库
		KeyStore keyStore = getKeyStore(keyStorePath, password);
		// 初始化密钥工厂
		keyManagerFactory.init(keyStore, password.toCharArray());
		// 实例化信任库
		TrustManagerFactory trustManagerFactory = TrustManagerFactory
				.getInstance(TrustManagerFactory.getDefaultAlgorithm());
		// 获得信任库
		KeyStore trustStore = getKeyStore(trustStorePath, password);
		// 初始化信任库
		trustManagerFactory.init(trustStore);
		// 实例化SSL上下文
		SSLContext ctx = SSLContext.getInstance(PROTOCOL);
		// 初始化SSL上下文
		ctx.init(keyManagerFactory.getKeyManagers(), trustManagerFactory.getTrustManagers(), new SecureRandom());
		// 获得SSLSocketFactory
		return ctx.getSocketFactory();

	}

	/**
	 * 为HttpsURLConnection配置SSLSocketFactory
	 * 
	 * @param conn HttpsURLConnection
	 * @param password 密码
	 * @param keyStorePath 密钥库路径
	 * @param trustKeyStorePath 信任库路径
	 * @throws Exception
	 */
	public static void configSSLSocketFactory(HttpsURLConnection conn, String password, String keyStorePath,
			String trustKeyStorePath) throws Exception {
		// 获得SSLSocketFactory
		SSLSocketFactory sslSocketFactory = getSSLSocketFactory(password, keyStorePath, trustKeyStorePath);
		// 设置SSLSocketFactory
		conn.setSSLSocketFactory(sslSocketFactory);
	}
}
