package org.ibase4j.core.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * 身份证验证辅助类
 * 
 * @author ShenHuaJie
 * @since 2011-11-08
 */
public final class IDCardUtil {
	private IDCardUtil() {
	}

	static final char[] code = { '1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2' }; // 11个校验码字符
	static final int[] factor = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1 }; // 18个加权因子

	/**
	 * 修补15位居民身份证号码为18位，并校验15位身份证有效性
	 * 
	 * @param personIDCode 十五位身份证号码
	 * @return String 十八位身份证号码
	 * @throws 无效的身份证号
	 */
	public static final String fixPersonIDCodeWithCheck(String personIDCode) throws Throwable {
		if (personIDCode == null || personIDCode.trim().length() != 15)
			throw new RuntimeException("输入的身份证号不足15位，请检查");

		if (!isIdentity(personIDCode))
			throw new RuntimeException("输入的身份证号无效，请检查");

		return fixPersonIDCodeWithoutCheck(personIDCode);
	}

	/**
	 * 修补15位居民身份证号码为18位，不校验身份证有效性
	 * 
	 * @param personIDCode 十五位身份证号码
	 * @return 十八位身份证号码
	 * @throws 身份证号参数不是15位
	 */
	public static final String fixPersonIDCodeWithoutCheck(String personIDCode) {
		if (personIDCode == null || personIDCode.trim().length() != 15)
			throw new RuntimeException("输入的身份证号不足15位，请检查");

		String id17 = personIDCode.substring(0, 6) + "19" + personIDCode.substring(6, 15); // 15位身份证补'19'

		int[] idcd = new int[18];
		int sum; // 根据公式 ∑(ai×Wi) 计算
		int remainder; // 第18位校验码
		for (int i = 0; i < 17; i++) {
			idcd[i] = Integer.parseInt(id17.substring(i, i + 1));
		}
		sum = 0;
		for (int i = 0; i < 17; i++) {
			sum = sum + idcd[i] * factor[i];
		}
		remainder = sum % 11;
		String lastCheckBit = String.valueOf(code[remainder]);
		return id17 + lastCheckBit;
	}

	/**
	 * 从身份证号中获取出生日期，身份证号可以为15位或18位
	 * 
	 * @param identity 身份证号
	 * @return 出生日期
	 * @throws 身份证号出生日期段有误
	 */
	public static final Timestamp getBirthdayFromPersonIDCode(String identity) throws Throwable {
		String id = getFixedPersonIDCode(identity);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		try {
			Timestamp birthday = new Timestamp(sdf.parse(id.substring(6, 14)).getTime());
			return birthday;
		} catch (ParseException e) {
			throw new RuntimeException("不是有效的身份证号，请检查");
		}
	}

	/**
	 * 将传入的身份证号码进行校验，并返回一个对应的18位身份证
	 * 
	 * @param personIDCode 身份证号码
	 * @return String 十八位身份证号码
	 * @throws 无效的身份证号
	 */
	public static final String getFixedPersonIDCode(String personIDCode) throws Throwable {
		if (personIDCode == null)
			throw new RuntimeException("输入的身份证号无效，请检查");

		if (personIDCode.length() == 18) {
			if (isIdentity(personIDCode))
				return personIDCode;
			else
				throw new RuntimeException("输入的身份证号无效，请检查");
		} else if (personIDCode.length() == 15)
			return fixPersonIDCodeWithCheck(personIDCode);
		else
			throw new RuntimeException("输入的身份证号无效，请检查");
	}

	/**
	 * 从身份证号获取性别
	 * 
	 * @param identity 身份证号
	 * @return 性别代码
	 * @throws Exception 无效的身份证号码
	 */
	public static final Sex getGenderFromPersonIDCode(String identity) throws Throwable {
		String id = getFixedPersonIDCode(identity);
		char sex = id.charAt(16);
		return sex % 2 == 0 ? Sex.Female : Sex.Male;
	}

	/**
	 * 判断是否是有效的18位或15位居民身份证号码
	 * 
	 * @param identity 18位或15位居民身份证号码
	 * @return 是否为有效的身份证号码
	 */
	public static final boolean isIdentity(String identity) {
		if (identity == null)
			return false;
		if (identity.length() == 18 || identity.length() == 15) {
			String id15 = null;
			if (identity.length() == 18)
				id15 = identity.substring(0, 6) + identity.substring(8, 17);
			else
				id15 = identity;
			try {
				Long.parseLong(id15); // 校验是否为数字字符串

				String birthday = "19" + id15.substring(6, 12);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
				sdf.parse(birthday); // 校验出生日期
				if (identity.length() == 18 && !fixPersonIDCodeWithoutCheck(id15).equals(identity))
					return false; // 校验18位身份证
			} catch (Exception e) {
				return false;
			}
			return true;
		} else
			return false;
	}

	public static void main(String[] args) throws Throwable {
		String idcard1 = "11010519491231002X";
		String idcard2 = "440524188001010014";
		System.out.println(IDCardUtil.getGenderFromPersonIDCode(idcard1));
		System.out.println(IDCardUtil.isIdentity(idcard2));
	}

	/**
	 * 性别
	 * 
	 * @author ShenHuaJie
	 */
	public enum Sex {
		/**
		 * 未知
		 */
		Other("未知", 0),
		/**
		 * 男
		 */
		Male("男", 1),
		/**
		 * 女
		 */
		Female("女", 2);

		private String name;
		private Integer value;

		private Sex(String name, Integer value) {
			this.name = name;
			this.value = value;
		}

		public Integer getValue() {
			return value;
		}

		public String toString() {
			return this.name;
		}
	}
}
