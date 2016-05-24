package org.ibase4j.core.support.fasetdfs;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.activation.MimetypesFileTypeMap;

@SuppressWarnings("serial")
public class FastDFSFile implements FileManagerConfig {
	private byte[] content;
	private String ext;

	private String mime;
	private String size;
	private String filename;

	public FastDFSFile(String filePath) {
		this.ext = filePath.substring(filePath.lastIndexOf(".") + 1);
		byte[] file_buff = null;
		FileInputStream fileInputStream = null;
		try {
			File file = new File(filePath);
			this.size = String.valueOf(file.length());
			this.filename = file.getName();

			fileInputStream = new FileInputStream(file);
			if (fileInputStream != null) {
				int len = fileInputStream.available();
				file_buff = new byte[len];
				fileInputStream.read(file_buff);
			}
			this.content = file_buff;
			InputStream is = getClass().getResourceAsStream("/META-INF/mime.types");
			MimetypesFileTypeMap mimetypesFileTypeMap = new MimetypesFileTypeMap(is);
			this.mime = mimetypesFileTypeMap.getContentType(filename);
		} catch (Exception e) {
		} finally {
			if (fileInputStream != null) {
				try {
					fileInputStream.close();
				} catch (IOException e) {
				}
			}
		}
	}

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

	public String getExt() {
		return ext;
	}

	public void setExt(String ext) {
		this.ext = ext;
	}

	public String getMime() {
		return mime;
	}

	public void setMime(String mime) {
		this.mime = mime;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}
}
