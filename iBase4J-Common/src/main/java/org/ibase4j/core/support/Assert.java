package org.ibase4j.core.support;

import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.ibase4j.core.config.Resources;
import org.ibase4j.core.util.IDCardUtil;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

/**
 * Assertion utility class that assists in validating arguments.
 * 
 * @author ShenHuaJie
 * @version 2016年6月8日 下午6:37:02
 */
public abstract class Assert {
	private static String getMessage(String key, Object... args) {
		return Resources.getMessage(key, args);
	}

	/**  */
	public static void isTrue(boolean expression, String key) {
		if (!expression) {
			throw new IllegalArgumentException(getMessage(key));
		}
	}

	/**  */
	public static void isNull(Object object, String key) {
		if (object != null) {
			throw new IllegalArgumentException(getMessage(key));
		}
	}

	/** (key_IS_NULL) */
	public static void notNull(Object object, String key, Object... args) {
		if (object == null) {
			throw new IllegalArgumentException(getMessage(key + "_IS_NULL", args));
		}
	}

	/**  */
	public static void hasLength(String text, String key) {
		if (StringUtils.isEmpty(text)) {
			throw new IllegalArgumentException(getMessage(key));
		}
	}

	/**  */
	public static void hasText(String text, String key) {
		if (StringUtils.isBlank(text)) {
			throw new IllegalArgumentException(getMessage(key));
		}
	}

	/**  */
	public static void doesNotContain(String textToSearch, String substring, String key) {
		if (StringUtils.isNotBlank(textToSearch) && StringUtils.isNotBlank(substring)
				&& textToSearch.contains(substring)) {
			throw new IllegalArgumentException(getMessage(key));
		}
	}

	/** */
	public static void notEmpty(Object[] array, String key, Object... args) {
		if (ObjectUtils.isEmpty(array)) {
			throw new IllegalArgumentException(getMessage(key + "_IS_EMPTY", args));
		}
	}

	/**  */
	public static void noNullElements(Object[] array, String key) {
		if (array != null) {
			for (Object element : array) {
				if (element == null) {
					throw new IllegalArgumentException(getMessage(key));
				}
			}
		}
	}

	/**  */
	public static void notEmpty(Collection<?> collection, String key) {
		if (CollectionUtils.isEmpty(collection)) {
			throw new IllegalArgumentException(getMessage(key));
		}
	}

	/**  */
	public static void notEmpty(Map<?, ?> map, String key) {
		if (CollectionUtils.isEmpty(map)) {
			throw new IllegalArgumentException(getMessage(key));
		}
	}

	/**  */
	public static void isInstanceOf(Class<?> type, Object obj, String key) {
		notNull(type, key);
		if (!type.isInstance(obj)) {
			throw new IllegalArgumentException(getMessage(key));
		}
	}

	/**  */
	public static void isAssignable(Class<?> superType, Class<?> subType, String key) {
		notNull(superType, key);
		if (subType == null || !superType.isAssignableFrom(subType)) {
			throw new IllegalArgumentException(getMessage(key));
		}
	}

	/** 空字符或NULL */
	public static void isBlank(String text, String key) {
		if (StringUtils.isNotBlank(text)) {
			throw new IllegalArgumentException(getMessage(key));
		}
	}

	/** 非空字符串(key_IS_NULL) */
	public static void isNotBlank(String text, String key) {
		if (StringUtils.isBlank(text)) {
			throw new IllegalArgumentException(getMessage(key + "_IS_NULL"));
		}
	}

	/** 允许最小值 */
	public static void min(Integer value, Integer min, String key) {
		notNull(value, key);
		if (value < min) {
			throw new IllegalArgumentException(getMessage(key + "_MIN", min));
		}
	}

	/** 允许最大值 */
	public static void max(Integer value, Integer max, String key) {
		notNull(value, key);
		if (value > max) {
			throw new IllegalArgumentException(getMessage(key + "_MAX", max));
		}
	}

	/** 允许值范围 */
	public static void range(Integer value, Integer min, Integer max, String key) {
		min(value, min, key);
		max(value, max, key);
	}

	/** 允许最小值 */
	public static void min(Float value, Float min, String key) {
		notNull(value, key);
		if (value < min) {
			throw new IllegalArgumentException(getMessage(key + "_MIN", min));
		}
	}

	/** 允许最大值 */
	public static void max(Float value, Float max, String key) {
		notNull(value, key);
		if (value > max) {
			throw new IllegalArgumentException(getMessage(key + "_MAX", max));
		}
	}

	/** 允许值范围 */
	public static void range(Float value, Float min, Float max, String key) {
		min(value, min, key);
		max(value, max, key);
	}

	/** 允许最小值 */
	public static void min(Double value, Double min, String key) {
		notNull(value, key);
		if (value < min) {
			throw new IllegalArgumentException(getMessage(key + "_MIN", min));
		}
	}

	/** 允许最大值 */
	public static void max(Double value, Double max, String key) {
		notNull(value, key);
		if (value > max) {
			throw new IllegalArgumentException(getMessage(key + "_MAX", max));
		}
	}

	/** 允许值范围 */
	public static void range(Double value, Double min, Double max, String key) {
		min(value, min, key);
		max(value, max, key);
	}

	/** 字符长度(key_LENGTH) */
	public static void length(String text, Integer min, Integer max, String key) {
		notNull(text, key);
		if (min != null && text.length() < min) {
			throw new IllegalArgumentException(getMessage(key + "_LENGTH", min, max));
		}
		if (max != null && text.length() > max) {
			throw new IllegalArgumentException(getMessage(key + "_LENGTH", min, max));
		}
	}

	/** 未来某一天 */
	public static void future(Date date, String key) {
		if (date != null && date.compareTo(new Date()) <= 0) {
			throw new IllegalArgumentException(getMessage(key + "_NOT_FUTURE"));
		}
	}

	/** 身份证 */
	public static void idCard(String text) {
		if (!IDCardUtil.isIdentity(text)) {
			throw new IllegalArgumentException(getMessage("IDCARD_ILLEGAL"));
		}
	}

	/** 邮箱 */
	public static void email(String text) {
		String regex = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
		pattern(text, regex, true, getMessage("EMAIL_ILLEGAL"));
	}

	/** 手机号 */
	public static void mobile(String text) {
		String regex = "((^(13|15|17|18)[0-9]{9}$)|(^0[1,2]{1}\\d{1}-?\\d{8}$)|(^0[3-9] {1}\\d{2}-?\\d{7,8}$)|(^0[1,2]{1}\\d{1}-?\\d{8}-(\\d{1,4})$)|(^0[3-9]{1}\\d{2}-? \\d{7,8}-(\\d{1,4})$))";
		pattern(text, regex, true, getMessage("MOBILE_ILLEGAL"));
	}

	/** 正则表达式 */
	public static void pattern(String text, String regex, boolean flag, String key) {
		boolean result = false;
		try {
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(text);
			result = matcher.matches();
		} catch (Exception e) {
			result = false;
		}
		if (result != flag) {
			throw new IllegalArgumentException(getMessage(key + "_ILLEGAL"));
		}
	}
}
