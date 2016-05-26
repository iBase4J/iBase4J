package org.ibase4j.core.support.spring.data.redis;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.util.Assert;

/**
 * @author ShenHuaJie
 * @version 2016年5月26日 上午8:49:58
 */
public class ObjectRedisSerializer implements RedisSerializer<Object> {
	private final Converter<Object, byte[]> serializer;
	private final Converter<byte[], Object> deserializer;

	/**
	 * Creates a new {@link JdkSerializationRedisSerializer} using the default
	 * class loader.
	 */
	public ObjectRedisSerializer() {
		this(new SerializingConverter(), new DeserializingConverter());
	}

	/**
	 * Creates a new {@link JdkSerializationRedisSerializer} using a
	 * {@link ClassLoader}.
	 *
	 * @param classLoader
	 * @since 1.7
	 */
	public ObjectRedisSerializer(ClassLoader classLoader) {
		this(new SerializingConverter(), new DeserializingConverter(classLoader));
	}

	/**
	 * Creates a new {@link JdkSerializationRedisSerializer} using a
	 * {@link Converter converters} to serialize and deserialize objects.
	 * 
	 * @param serializer must not be {@literal null}
	 * @param deserializer must not be {@literal null}
	 * @since 1.7
	 */
	public ObjectRedisSerializer(Converter<Object, byte[]> serializer, Converter<byte[], Object> deserializer) {
		Assert.notNull("Serializer must not be null!");
		Assert.notNull("Deserializer must not be null!");

		this.serializer = serializer;
		this.deserializer = deserializer;
	}

	public Object deserialize(byte[] bytes) {
		if (bytes == null || bytes.length == 0) {
			return null;
		}

		try {
			return deserializer.convert(bytes);
		} catch (Exception ex) {
			throw new SerializationException("Cannot deserialize", ex);
		}
	}

	public byte[] serialize(Object object) {
		if (object == null) {
			return new byte[0];
		}
		try {
			return serializer.convert(object);
		} catch (Exception ex) {
			throw new SerializationException("Cannot serialize", ex);
		}
	}

}
