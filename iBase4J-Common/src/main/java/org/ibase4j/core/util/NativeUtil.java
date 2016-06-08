package org.ibase4j.core.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public final class NativeUtil {
	private NativeUtil() {
	}

	/** 获取网卡序列号 */
	public static final String getDUID() {
		String address = "";
		String command = "cmd.exe /c ipconfig /all";
		try {
			Process p = Runtime.getRuntime().exec(command);
			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line;
			while ((line = br.readLine()) != null) {
				if (line.indexOf("DUID") > 0) {
					int index = line.indexOf(":");
					index += 2;
					address = line.substring(index);
					break;
				}
			}
			br.close();
		} catch (IOException e) {
		}
		return address;
	}
}
