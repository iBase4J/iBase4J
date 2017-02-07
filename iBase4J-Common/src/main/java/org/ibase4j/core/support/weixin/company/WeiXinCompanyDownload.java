package org.ibase4j.core.support.weixin.company;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * 根据微信media_id下载工单图片
 * 
 * @author ShenHuaJie
 * @since 2017年2月3日 下午5:13:06
 */
public class WeiXinCompanyDownload implements Runnable {
	private String[] media_ids;

	public WeiXinCompanyDownload(String[] media_ids) {
		this.media_ids = media_ids;
	}

	@Override
	public void run() {
		int len = media_ids.length;
		for (int i = 0; i < len; i++) {
			@SuppressWarnings("unused")
			String fileName = download(media_ids[i]);
		}
	}

	public static String download(String media_id) {
		try {
			String urlStr = "https://qyapi.weixin.qq.com/cgi-bin/media/get?access_token="
					+ WeiXinCompanyUtils.getToken() + "&media_id=" + media_id;

			URL url = new URL(urlStr);
			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
			conn.setDoInput(true);

			String disposition = conn.getHeaderField("Content-disposition");
			if (disposition == null) {
				return null;
			}

			String[] s = disposition.split(";");

			String ss[] = s[1].trim().split("\\=");
			String fileName = ss[1].trim().replaceAll("\"", "");
			// fileName.getBytes("iso-8859-1");
			fileName = new String(fileName.getBytes("iso-8859-1"), "utf-8");

			String path = "/www/gd_image/" + fileName;
			File file = new File(path);
			if (!file.exists()) {
				file.createNewFile();
			}
			FileOutputStream fos = new FileOutputStream(file);
			InputStream input = conn.getInputStream();
			byte[] bytes = new byte[2048];
			int size = 0;
			while ((size = input.read(bytes)) != -1) {
				fos.write(bytes, 0, size);
			}
			input.close();
			fos.close();
			return fileName;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
