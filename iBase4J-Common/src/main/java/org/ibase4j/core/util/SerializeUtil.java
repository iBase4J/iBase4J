package org.ibase4j.core.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 序列化辅助类
 * 
 * @author ShenHuaJie
 * @version $Id: SerializableUtil.java, v 0.1 2014-6-10 下午1:30:39 ShenHuaJie Exp
 *          $
 */
public class SerializeUtil {
	private SerializeUtil(){}

	/**
	 * 序列化
	 * 
	 * @param object
	 * @return
	 */
	public static byte[] toBytes(Object object) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(baos);
			oos.writeObject(object);
			return baos.toByteArray();
		} catch (IOException ex) {
			throw new RuntimeException(ex.getMessage(), ex);
		} finally {
			try {
				oos.close();
				baos.close();
			} catch (Exception e) {
			}
		}
	}

	/**
	 * 反序列化
	 * 
	 * @param bytes
	 * @return
	 */
	public static Object toObject(byte[] bytes) {
		ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(bais);
			return ois.readObject();
		} catch (IOException ex) {
			throw new RuntimeException(ex.getMessage(), ex);
		} catch (ClassNotFoundException ex) {
			throw new RuntimeException(ex.getMessage(), ex);
		} finally {
			try {
				ois.close();
				bais.close();
			} catch (Exception e) {
			}
		}
	}
}
