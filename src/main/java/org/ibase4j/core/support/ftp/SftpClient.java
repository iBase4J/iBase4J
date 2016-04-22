package org.ibase4j.core.support.ftp;

import java.io.File;
import java.util.Properties;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.PropertySource;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

/**
 * Java Secure Channel
 * 
 * @author
 */
@PropertySource("classpath:config/ssh.properties")
public class SftpClient {
	private Logger logger = LogManager.getLogger();
	private ResourceBundle bundle = ResourceBundle.getBundle("config/ssh");
	private Session session = null;
	private ChannelSftp channel = null;

	private SftpClient() {
	}

	public static SftpClient connect() {
		return new SftpClient().init();
	}

	public SftpClient init() {
		try {
			Properties config = new Properties();
			String host = bundle.getString("host");
			int port = Integer.valueOf(bundle.getString("port"));
			String userName = bundle.getString("user.name");
			String password = bundle.getString("user.password");
			int timeout = Integer.valueOf(bundle.getString("timeout"));
			int aliveMax = Integer.valueOf(bundle.getString("aliveMax"));
			JSch jsch = new JSch(); // 创建JSch对象
			session = jsch.getSession(userName, host, port); // 根据用户名，主机ip，端口获取一个Session对象
			if (password != null) {
				session.setPassword(password); // 设置密码
			}
			config.put("userauth.gssapi-with-mic", "no");
			config.put("StrictHostKeyChecking", "no");
			session.setConfig(config); // 为Session对象设置properties
			session.setTimeout(timeout); // 设置timeout时间
			session.setServerAliveCountMax(aliveMax);
			session.connect(); // 通过Session建立链接
			channel = (ChannelSftp) session.openChannel("sftp"); // 打开SFTP通道
			channel.connect(); // 建立SFTP通道的连接
			logger.info("SSH Channel connected.");
		} catch (JSchException e) {
			throw new FtpException("", e);
		}
		return this;
	}

	public void disconnect() {
		if (channel != null) {
			channel.disconnect();
		}
		if (session != null) {
			session.disconnect();
			logger.info("SSH Channel disconnected.");
		}
	}

	public void put(String src, String dst) {
		File file = new File(src);
		long fileSize = file.length();
		try {
			channel.put(src, dst, new FileProgressMonitor(fileSize));
		} catch (SftpException e) {
			throw new FtpException("", e);
		}
	}
}
