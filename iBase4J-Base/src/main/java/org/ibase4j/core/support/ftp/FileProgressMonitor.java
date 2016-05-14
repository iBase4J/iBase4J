package org.ibase4j.core.support.ftp;

import java.text.DecimalFormat;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.jcraft.jsch.SftpProgressMonitor;

/** 监控 */
public class FileProgressMonitor implements SftpProgressMonitor {
	private final Logger logger = LogManager.getLogger();
	private long transfered; // 记录已传输的数据总大小
	private long fileSize; // 记录文件总大小
	private int minInterval = 100; // 打印日志时间间隔
	private long start; // 开始时间
	private DecimalFormat df = new DecimalFormat("#.##");
	private long preTime;

	/** 传输开始 */
	public void init(int op, String src, String dest, long max) {
		this.fileSize = max;
		logger.info("Transferring begin.");
		start = System.currentTimeMillis();
	}

	/** 传输中 */
	public boolean count(long count) {
		if (fileSize != 0 && transfered == 0) {
			logger.info("Transferring progress message: {}%", df.format(0));
			preTime = System.currentTimeMillis();
		}
		transfered += count;
		if (fileSize != 0) {
			long interval = System.currentTimeMillis() - preTime;
			if (transfered == fileSize || interval > minInterval) {
				preTime = System.currentTimeMillis();
				double d = ((double) transfered * 100) / (double) fileSize;
				logger.info("Transferring progress message: {}%", df.format(d));
			}
		} else {
			logger.info("Transferring progress message: " + transfered);
		}
		return true;
	}

	/** 传输结束 */
	public void end() {
		logger.info("Transferring end. used time: {}ms", System.currentTimeMillis() - start);
	}
}
