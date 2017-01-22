package org.ibase4j.core.support.cache;

import java.io.Serializable;
import java.util.Set;

public abstract class CacheManager {
	public abstract Object get(final String key);

	public abstract Set<Serializable> getAll(final String pattern);

	public abstract void set(final String key, final Serializable value, int seconds);

	public abstract void set(final String key, final Serializable value);

	public abstract Boolean exists(final String key);

	public abstract void del(final String key);

	public abstract void delAll(final String pattern);

	public abstract String type(final String key);

	public abstract Boolean expire(final String key, final int seconds);

	public abstract Boolean expireAt(final String key, final long unixTime);

	public abstract Long ttl(final String key);

	public abstract Serializable getSet(final String key, final String value);
}
