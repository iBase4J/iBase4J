package org.ibase4j.core.support.spring.data.redis;

import java.io.ByteArrayOutputStream;
import java.util.zip.GZIPOutputStream;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.serializer.DefaultSerializer;
import org.springframework.core.serializer.Serializer;
import org.springframework.core.serializer.support.SerializationFailedException;
import org.springframework.util.Assert;

/**
 * @author ShenHuaJie
 * @version 2016年5月26日 上午8:49:58
 */
public class SerializingConverter implements Converter<Object, byte[]> {
	private final Serializer<Object> serializer;

	/**
	 * Create a default {@code SerializingConverter} that uses standard Java
	 * serialization.
	 */
	public SerializingConverter() {
		this.serializer = new DefaultSerializer();
	}

	/**
	 * Create a {@code SerializingConverter} that delegates to the provided
	 * {@link Serializer}.
	 */
	public SerializingConverter(Serializer<Object> serializer) {
		Assert.notNull(serializer, "Serializer must not be null");
		this.serializer = serializer;
	}

	/**
	 * Serializes the source object and returns the byte array result.
	 */
	public byte[] convert(Object source) {
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream(1024);
		try {
			// 建立gzip压缩输出流
			GZIPOutputStream gzipStream = new GZIPOutputStream(byteStream);
			this.serializer.serialize(source, gzipStream);
			gzipStream.close();
			return byteStream.toByteArray();
		} catch (Throwable ex) {
			throw new SerializationFailedException(
					"Failed to serialize object using " + this.serializer.getClass().getSimpleName(), ex);
		}
	}
}
