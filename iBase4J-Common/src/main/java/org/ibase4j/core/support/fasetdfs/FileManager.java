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

@SuppressWarnings("serial")
public class FileManager implements FileManagerConfig {
	private static Logger logger = LogManager.getLogger();
	private static TrackerClient trackerClient;
	private static TrackerServer trackerServer;
	private static StorageServer storageServer;
	private static StorageClient storageClient;

	static { // Initialize Fast DFS Client configurations
		try {
			String classPath = new File(FileManager.class.getResource("/").getFile()).getCanonicalPath();
			String fdfsClientConfigFilePath = classPath + File.separator + CLIENT_CONFIG_FILE;
			logger.info("Fast DFS configuration file path:" + fdfsClientConfigFilePath);
			ClientGlobal.init(fdfsClientConfigFilePath);
			trackerClient = new TrackerClient();
			trackerServer = trackerClient.getConnection();
			storageClient = new StorageClient(trackerServer, storageServer);
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	public static String upload(FastDFSFile file) {
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

		logger.info("upload file successfully!!!  " + "group_name: " + groupName + ", remoteFileName:" + " "
				+ remoteFileName);
		return fileAbsolutePath;
	}

	public static FileInfo getFile(String groupName, String remoteFileName) {
		try {
			return storageClient.get_file_info(groupName, remoteFileName);
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
