package org.ibase4j.core.support.ftp;

import java.text.DecimalFormat;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.jcraft.jsch.SftpProgressMonitor;

/** 监控 */
public class FileProgressMonitor implements SftpProgressMonitor {
	private Logger logger = LogManager.getLogger();
	private long transfered; // 记录已传输的数据总大小
	private long fileSize; // 记录文件总大小
	private int minInterval = 6; // 打印日志时间间隔
	private long start; // 开始时间
	private DecimalFormat df = new DecimalFormat("#.##");
	private double cacheProgress; //

	public FileProgressMonitor(long fileSize) {
		this.fileSize = fileSize;
	}

	public void init(int op, String src, String dest, long max) {
		logger.info("Transferring begin.");
		start = System.currentTimeMillis();
	}

	/**
	 * 实现了SftpProgressMonitor接口的count方法
	 */
	public boolean count(long count) {
		if (fileSize != 0 && transfered == 0) {
			logger.info("Sending progress message: {}%", df.format(0));
		}
		add(count);
		if (fileSize != 0) {
			double ad = ((double) count * 100) / (double) fileSize;
			if (transfered == fileSize || cacheProgress > minInterval) {
				cacheProgress = 0;
				double d = ((double) transfered * 100) / (double) fileSize;
				logger.info("Sending progress message: {}%", df.format(d));
			} else {
				cacheProgress += ad;
			}
		} else {
			logger.info("Sending progress message: " + transfered);
		}
		return true;
	}

	/**
	 * 实现了SftpProgressMonitor接口的end方法
	 */
	public void end() {
		logger.info("transfering end.use time: {}ms", System.currentTimeMillis() - start);
	}

	private void add(long count) {
		transfered = transfered + count;
	}

}
