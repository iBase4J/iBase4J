package org.ibase4j.core.support;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.ibase4j.core.util.IDCardUtil;

/**
 * Assertion utility class that assists in validating arguments.
 * 
 * @author ShenHuaJie
 * @version 2016年6月8日 下午6:37:02
 */
public abstract class Assert extends org.springframework.util.Assert {
	/** 空字符或NULL */
	public static void isBlank(String text, String message) {
		if (StringUtils.isNotBlank(text)) {
			throw new IllegalArgumentException(message);
		}
	}

	/** 非空字符串 */
	public static void isNotBlank(String text, String message) {
		if (StringUtils.isBlank(text)) {
			throw new IllegalArgumentException(message);
		}
	}

	/** 允许最小值 */
	public static void min(Integer value, Integer min, String message) {
		notNull(value, message);
		if (value < min) {
			throw new IllegalArgumentException(message);
		}
	}

	/** 允许最大值 */
	public static void max(Integer value, Integer max, String message) {
		notNull(value, message);
		if (value > max) {
			throw new IllegalArgumentException(message);
		}
	}

	/** 允许值范围 */
	public static void range(Integer value, Integer min, Integer max, String message) {
		min(value, min, message);
		max(value, max, message);
	}

	/** 允许最小值 */
	public static void min(Float value, Float min, String message) {
		notNull(value, message);
		if (value < min) {
			throw new IllegalArgumentException(message);
		}
	}

	/** 允许最大值 */
	public static void max(Float value, Float max, String message) {
		notNull(value, message);
		if (value > max) {
			throw new IllegalArgumentException(message);
		}
	}

	/** 允许值范围 */
	public static void range(Float value, Float min, Float max, String message) {
		min(value, min, message);
		max(value, max, message);
	}

	/** 允许最小值 */
	public static void min(Double value, Double min, String message) {
		notNull(value, message);
		if (value < min) {
			throw new IllegalArgumentException(message);
		}
	}

	/** 允许最大值 */
	public static void max(Double value, Double max, String message) {
		notNull(value, message);
		if (value > max) {
			throw new IllegalArgumentException(message);
		}
	}

	/** 允许值范围 */
	public static void range(Double value, Double min, Double max, String message) {
		min(value, min, message);
		max(value, max, message);
	}

	/** 字符长度 */
	public static void length(String text, Integer min, Integer max, String message) {
		notNull(text, message);
		if (min != null && text.length() < min) {
			throw new IllegalArgumentException(message);
		}
		if (max != null && text.length() > max) {
			throw new IllegalArgumentException(message);
		}
	}

	/** 未来某一天 */
	public static void future(Date date, String message) {
		if (date != null && date.compareTo(new Date()) <= 0) {
			throw new IllegalArgumentException(message);
		}
	}

	/** 身份证 */
	public static void idCard(String text, String message) {
		if (!IDCardUtil.isIdentity(text)) {
			throw new IllegalArgumentException(message);
		}
	}

	/** 邮箱 */
	public static void email(String text, String message) {
		String regex = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
		pattern(text, regex, true, message);
	}

	/** 手机号 */
	public static void mobile(String text, String message) {
		String regex = "((^(13|15|17|18)[0-9]{9}$)|(^0[1,2]{1}\\d{1}-?\\d{8}$)|(^0[3-9] {1}\\d{2}-?\\d{7,8}$)|(^0[1,2]{1}\\d{1}-?\\d{8}-(\\d{1,4})$)|(^0[3-9]{1}\\d{2}-? \\d{7,8}-(\\d{1,4})$))";
		pattern(text, regex, true, message);
	}

	/** 正则表达式 */
	public static void pattern(String text, String regex, boolean flag, String message) {
		boolean result = false;
		try {
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(text);
			result = matcher.matches();
		} catch (Exception e) {
			result = false;
		}
		if (result != flag) {
			throw new IllegalArgumentException(message);
		}
	}
}
