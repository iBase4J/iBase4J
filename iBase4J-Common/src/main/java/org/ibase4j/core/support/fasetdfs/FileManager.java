package org.ibase4j.core.support.fasetdfs;

import java.io.File;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.FileInfo;
import org.csource.fastdfs.ServerInfo;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.csource.fastdht.FastDHTClient;
import org.csource.fastdht.KeyInfo;

import com.alibaba.fastjson.JSON;

/**
 * @author ShenHuaJie
 * @version 2016年6月27日 上午9:51:06
 */
@SuppressWarnings("serial")
public class FileManager implements Config {
	private static Logger logger = LogManager.getLogger();
	private static TrackerClient trackerClient;
	private static TrackerServer trackerServer;
	private static StorageServer storageServer;
	private static StorageClient storageClient;
	private static FastDHTClient fastDHTClient;

	static { // Initialize Fast DFS Client configurations
		try {
			String classPath = new File(FileManager.class.getResource("/").getFile()).getCanonicalPath();
			String fdfsClientConfigFilePath = classPath + File.separator + DFS_CLIENT_CONFIG_FILE;
			String fdhtClientConfigFilePath = classPath + File.separator + DHT_CLIENT_CONFIG_FILE;
			logger.info("Fast DFS configuration file path:" + fdfsClientConfigFilePath);
			ClientGlobal.init(fdfsClientConfigFilePath);
			org.csource.fastdht.ClientGlobal.init(fdhtClientConfigFilePath);
			trackerClient = new TrackerClient();
			trackerServer = trackerClient.getConnection();
			storageClient = new StorageClient(trackerServer, storageServer);
			fastDHTClient = new FastDHTClient(true);
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	public static void upload(FileModel file) {
		logger.info("File Name: " + file.getFilename() + ". File Length: " + file.getContent().length);

		NameValuePair[] meta_list = new NameValuePair[] { new NameValuePair("mime", file.getMime()),
				new NameValuePair("size", file.getSize()), new NameValuePair("filename", file.getFilename()) };

		long startTime = System.currentTimeMillis();
		String[] uploadResults = null;
		try {
			uploadResults = storageClient.upload_file(file.getContent(), file.getExt(), meta_list);
		} catch (IOException e) {
			logger.error("IO Exception when uploadind the file: " + file.getFilename(), e);
		} catch (Exception e) {
			logger.error("Non IO Exception when uploadind the file: " + file.getFilename(), e);
		}
		logger.info("upload_file time used: " + (System.currentTimeMillis() - startTime) + " ms");

		if (uploadResults == null) {
			logger.error("upload file fail, error code: " + storageClient.getErrorCode());
		}

		String groupName = uploadResults[0];
		String remoteFileName = uploadResults[1];

		String fileAbsolutePath = PROTOCOL + trackerServer.getInetSocketAddress().getHostName() + SEPARATOR
				+ TRACKER_NGNIX_PORT + SEPARATOR + groupName + SEPARATOR + remoteFileName;
		file.setRemotePath(fileAbsolutePath);
		logger.info("upload file successfully!!!  " + "group_name: " + groupName + ", remoteFileName:" + " "
				+ remoteFileName);
		try {
			KeyInfo keyInfo = new KeyInfo(file.getNamespace(), file.getObjectId(), file.getKey());
			FastDfsFile fastDfsFile = new FastDfsFile();
			fastDfsFile.setGroupName(groupName);
			fastDfsFile.setFileName(remoteFileName);
			fastDfsFile.setNameValuePairs(meta_list);
			fastDHTClient.set(keyInfo, JSON.toJSONString(fastDfsFile));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	public static FileInfo getFile(String namespace, String objectId, String key) {
		try {
			KeyInfo keyInfo = new KeyInfo(namespace, objectId, key);
			String info = fastDHTClient.get(keyInfo);
			FastDfsFile fastDfsFile = JSON.parseObject(info, FastDfsFile.class);
			return storageClient.get_file_info(fastDfsFile.getGroupName(), fastDfsFile.getFileName());
		} catch (IOException e) {
			logger.error("IO Exception: Get File from Fast DFS failed", e);
		} catch (Exception e) {
			logger.error("Non IO Exception: Get File from Fast DFS failed", e);
		}
		return null;
	}

	public static void deleteFile(String groupName, String remoteFileName) throws Exception {
		storageClient.delete_file(groupName, remoteFileName);
	}

	public static StorageServer[] getStoreStorages(String groupName) throws IOException {
		return trackerClient.getStoreStorages(trackerServer, groupName);
	}

	public static ServerInfo[] getFetchStorages(String groupName, String remoteFileName) throws IOException {
		return trackerClient.getFetchStorages(trackerServer, groupName, remoteFileName);
	}
}
