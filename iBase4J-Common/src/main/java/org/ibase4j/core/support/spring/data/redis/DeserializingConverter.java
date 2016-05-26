package org.ibase4j.core.support.spring.data.redis;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.serializer.DefaultDeserializer;
import org.springframework.core.serializer.Deserializer;
import org.springframework.core.serializer.support.SerializationFailedException;
import org.springframework.util.Assert;

public class DeserializingConverter implements Converter<byte[], Object> {
	private final Deserializer<Object> deserializer;

	/**
	 * Create a {@code DeserializingConverter} with default
	 * {@link java.io.ObjectInputStream} configuration, using the
	 * "latest user-defined ClassLoader".
	 * 
	 * @see DefaultDeserializer#DefaultDeserializer()
	 */
	public DeserializingConverter() {
		this.deserializer = new DefaultDeserializer();
	}

	/**
	 * Create a {@code DeserializingConverter} for using an
	 * {@link java.io.ObjectInputStream} with the given {@code ClassLoader}.
	 * 
	 * @since 4.2.1
	 * @see DefaultDeserializer#DefaultDeserializer(ClassLoader)
	 */
	public DeserializingConverter(ClassLoader classLoader) {
		this.deserializer = new DefaultDeserializer(classLoader);
	}

	/**
	 * Create a {@code DeserializingConverter} that delegates to the provided
	 * {@link Deserializer}.
	 */
	public DeserializingConverter(Deserializer<Object> deserializer) {
		Assert.notNull(deserializer, "Deserializer must not be null");
		this.deserializer = deserializer;
	}

	public Object convert(byte[] source) {
		GZIPInputStream gzipStream = null;
		ByteArrayInputStream byteStream = new ByteArrayInputStream(source);
		try {
			// 建立gzip解压输入流
			gzipStream = new GZIPInputStream(byteStream);
			return this.deserializer.deserialize(gzipStream);
		} catch (Throwable ex) {
			throw new SerializationFailedException(
					"Failed to deserialize payload. " + "Is the byte array a result of corresponding serialization for "
							+ this.deserializer.getClass().getSimpleName() + "?",
					ex);
		} finally {
			if (gzipStream != null) {
				try {
					gzipStream.close();
				} catch (IOException e) {
				}
			}
		}
	}
}
