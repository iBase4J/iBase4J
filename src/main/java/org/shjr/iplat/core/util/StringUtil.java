package org.shjr.iplat.core.util;

import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 字符串辅助类
 * 
 * @author ShenHuaJie
 * @since 2011-11-08
 */
public class StringUtil {
    private static final String FOLDER_SEPARATOR    = "/";
    private static final char   EXTENSION_SEPARATOR = '.';
    private static String       HanDigiStr[]        = new String[] { "零", "壹", "贰", "叁", "肆", "伍",
            "陆", "柒", "捌", "玖"                     };

    private static String       HanDiviStr[]        = new String[] { "", "拾", "佰", "仟", "万", "拾",
            "佰", "仟", "亿", "拾", "佰", "仟", "万", "拾", "佰", "仟", "亿", "拾", "佰", "仟", "万", "拾", "佰",
            "仟"                                    };

    /**
     * 字符串组成类型<br>
     * number:数字字符串
     */
    public static final String  S_STYLE_N           = "number";

    /**
     * 字符串组成类型<br>
     * letter:字母字符串
     */
    public static final String  S_STYLE_L           = "letter";

    /**
     * 字符串组成类型<br>
     * numberletter:数字字母混合字符串
     */
    public static final String  S_STYLE_NL          = "numberletter";

    /**
     * 改变首字母大小写
     * 
     * @param str
     * @param capitalize
     * @return
     */
    public static String changeFirstCharacterCase(String str, boolean capitalize) {
        if (str == null || str.length() == 0) {
            return str;
        }
        StringBuffer buf = new StringBuffer(str.length());
        if (capitalize) {
            buf.append(Character.toUpperCase(str.charAt(0)));
        } else {
            buf.append(Character.toLowerCase(str.charAt(0)));
        }
        buf.append(str.substring(1));
        return buf.toString();
    }

    /**
     * Change the CharSet encoding from GBK to ISO.
     * 
     * @param value
     * @return
     */
    public final static String charsetGb2unicode(String value) throws UnsupportedEncodingException {
        return new String(value.getBytes("GB2312"));
    }

    /**
     * Change the CharSet encoding from ISO to GBK.
     * 
     * @param value
     * @return
     */
    public final static String charsetIso2gb(String value) {
        if (value == null || value.trim().equals("")) {
            return "";
        }
        try {
            return new String(value.getBytes("ISO8859_1"), "GBK");
        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
            return ex.getMessage();
        }
    }

    public final static String charsetUnicode2gb(String value) throws UnsupportedEncodingException {
        return new String(value.getBytes(), "GB2312");
    }

    /**
     * 判断数组中是否包含字符串
     * 
     * @param strArray
     * @param substring
     * @return
     */
    public static boolean contains(String[] strArray, String substring) {
        for (int i = 0; i < strArray.length; i++) {
            if (strArray[i] != null && strArray[i].equals(substring)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Delete all occurrences of the given substring.
     * 
     * @param pattern
     *            the pattern to delete all occurrences of
     */
    public static String delete(String inString, String pattern) {
        return replace(inString, pattern, "");
    }

    /**
     * Delete any character in a given string.
     * 
     * @param charsToDelete
     *            a set of characters to delete. E.g. "az\n" will delete 'a's,
     *            'z's and new lines.
     */
    public static String deleteAny(String inString, String charsToDelete) {
        if (inString == null || charsToDelete == null) {
            return inString;
        }
        StringBuffer out = new StringBuffer();
        for (int i = 0; i < inString.length(); i++) {
            char c = inString.charAt(i);
            if (charsToDelete.indexOf(c) == -1) {
                out.append(c);
            }
        }
        return out.toString();
    }

    /**
     * Take a String which is a delimited list and convert it to a String array.
     * <p>
     * A single delimiter can consists of more than one character: It will still
     * be considered as single delimiter string, rather than as bunch of
     * potential delimiter characters - in contrast to
     * <code>tokenizeToStringArray</code>.
     * 
     * @param str
     *            the input String
     * @param delimiter
     *            the delimiter between elements (this is a single delimiter,
     *            rather than a bunch individual delimiter characters)
     * @return an array of the tokens in the list
     * @see #tokenizeToStringArray
     */
    public static String[] delimitedListToStringArray(String str, String delimiter) {
        if (str == null) {
            return new String[0];
        }
        if (delimiter == null) {
            return new String[] { str };
        }
        List<String> result = InstanceUtil.newArrayList();
        if ("".equals(delimiter)) {
            for (int i = 0; i < str.length(); i++) {
                result.add(str.substring(i, i + 1));
            }
        } else {
            int pos = 0;
            int delPos = 0;
            while ((delPos = str.indexOf(delimiter, pos)) != -1) {
                result.add(str.substring(pos, delPos));
                pos = delPos + delimiter.length();
            }
            if (str.length() > 0 && pos <= str.length()) {
                // Add rest of String, but not in case of empty input.
                result.add(str.substring(pos));
            }
        }
        return toStringArray(result);
    }

    /**
     * Extract the filename from the given path, e.g. "mypath/myfile.txt" ->
     * "myfile.txt".
     * 
     * @param path
     *            the file path (may be <code>null</code>)
     * @return the extracted filename, or <code>null</code> if none
     */
    public static String getFilename(String path) {
        if (path == null) {
            return null;
        }
        int separatorIndex = path.lastIndexOf(FOLDER_SEPARATOR);
        return (separatorIndex != -1 ? path.substring(separatorIndex + 1) : path);
    }

    /**
     * Extract the filename extension from the given path, e.g.
     * "mypath/myfile.txt" -> "txt".
     * 
     * @param path
     *            the file path (may be <code>null</code>)
     * @return the extracted filename extension, or <code>null</code> if none
     */
    public static String getFilenameExtension(String path) {
        if (path == null) {
            return null;
        }
        int sepIndex = path.lastIndexOf(EXTENSION_SEPARATOR);
        return (sepIndex != -1 ? path.substring(sepIndex + 1) : null);
    }

    /**
     * 判断一个字符串是否由数字、字母、数字字母组成
     * 
     * @param pStr
     *            需要判断的字符串
     * @param pStyle
     *            判断规则
     * @return boolean 返回的布尔值
     */
    public static boolean isTheStyle(String pStr, String pStyle) {
        for (int i = 0; i < pStr.length(); i++) {
            char c = pStr.charAt(i);
            if (pStyle.equals(S_STYLE_N)) {
                if (!Character.isDigit(c))
                    return false;
            } else if (pStyle.equals(S_STYLE_L)) {
                if (!Character.isLetter(c))
                    return false;
            } else if (pStyle.equals(S_STYLE_NL)) {
                if (Character.isLetterOrDigit(c))
                    return false;
            }
        }
        return true;
    }

    /**
     * 合并字符串数组
     * 
     * @param a
     *            字符串数组0
     * @param b
     *            字符串数组1
     * @return 返回合并后的字符串数组
     */
    public static String[] mergeStringArray(String[] a, String[] b) {
        if (a.length == 0 || DataUtil.isEmpty(a))
            return b;
        if (b.length == 0 || DataUtil.isEmpty(b))
            return a;
        String[] c = new String[a.length + b.length];
        for (int m = 0; m < a.length; m++) {
            c[m] = a[m];
        }
        for (int i = 0; i < b.length; i++) {
            c[a.length + i] = b[i];
        }
        return c;
    }

    /**
     * 将货币转换为大写形式
     * 
     * @param val
     *            传入的数据
     * @return String 返回的人民币大写形式字符串
     */
    public static String numToRMBStr(double val) {
        String SignStr = "";
        String TailStr = "";
        long fraction, integer;
        int jiao, fen;

        if (val < 0) {
            val = -val;
            SignStr = "负";
        }
        if (val > 99999999999999.999 || val < -99999999999999.999)
            return "数值位数过大!";
        // 四舍五入到分
        long temp = Math.round(val * 100);
        integer = temp / 100;
        fraction = temp % 100;
        jiao = (int) fraction / 10;
        fen = (int) fraction % 10;
        if (jiao == 0 && fen == 0) {
            TailStr = "整";
        } else {
            TailStr = HanDigiStr[jiao];
            if (jiao != 0)
                TailStr += "角";
            // 零元后不写零几分
            if (integer == 0 && jiao == 0)
                TailStr = "";
            if (fen != 0)
                TailStr += HanDigiStr[fen] + "分";
        }
        // 下一行可用于非正规金融场合，0.03只显示“叁分”而不是“零元叁分”
        // if( !integer ) return SignStr+TailStr;
        return SignStr + PositiveIntegerToHanStr(String.valueOf(integer)) + "元" + TailStr;
    }

    /**
     * Replace all occurences of a substring within a string with another
     * string.
     * 
     * @param inString
     *            String to examine
     * @param oldPattern
     *            String to replace
     * @param newPattern
     *            String to insert
     * @return a String with the replacements
     */
    public static String replace(String inString, String oldPattern, String newPattern) {
        if (inString == null) {
            return null;
        }
        if (oldPattern == null || newPattern == null) {
            return inString;
        }

        StringBuffer sbuf = new StringBuffer();
        // output StringBuffer we'll build up
        int pos = 0; // our position in the old string
        int index = inString.indexOf(oldPattern);
        // the index of an occurrence we've found, or -1
        int patLen = oldPattern.length();
        while (index >= 0) {
            sbuf.append(inString.substring(pos, index));
            sbuf.append(newPattern);
            pos = index + patLen;
            index = inString.indexOf(oldPattern, pos);
        }
        sbuf.append(inString.substring(pos));

        // remember to append any characters to the right of a match
        return sbuf.toString();
    }

    /**
     * Split a String at the first occurrence of the delimiter. Does not include
     * the delimiter in the result.
     * 
     * @param toSplit
     *            the string to split
     * @param delimiter
     *            to split the string up with
     * @return a two element array with index 0 being before the delimiter, and
     *         index 1 being after the delimiter (neither element includes the
     *         delimiter); or <code>null</code> if the delimiter wasn't found in
     *         the given input String
     */
    public static String[] split(String toSplit, String delimiter) {
        if (toSplit == null || delimiter == null) {
            return null;
        }
        int offset = toSplit.indexOf(delimiter);
        if (offset < 0) {
            return null;
        }
        String beforeDelimiter = toSplit.substring(0, offset);
        String afterDelimiter = toSplit.substring(offset + delimiter.length());
        return new String[] { beforeDelimiter, afterDelimiter };
    }

    // ---------------------------------------------------------------------
    // Convenience methods for working with String arrays
    // ---------------------------------------------------------------------

    /**
     * Strip the filename extension from the given path, e.g.
     * "mypath/myfile.txt" -> "mypath/myfile".
     * 
     * @param path
     *            the file path (may be <code>null</code>)
     * @return the path with stripped filename extension, or <code>null</code>
     *         if none
     */
    public static String stripFilenameExtension(String path) {
        if (path == null) {
            return null;
        }
        int sepIndex = path.lastIndexOf(EXTENSION_SEPARATOR);
        return (sepIndex != -1 ? path.substring(0, sepIndex) : path);
    }

    /**
     * Tokenize the given String into a String array via a StringTokenizer.
     * Trims tokens and omits empty tokens.
     * <p>
     * The given delimiters string is supposed to consist of any number of
     * delimiter characters. Each of those characters can be used to separate
     * tokens. A delimiter is always a single character; for multi-character
     * delimiters, consider using <code>delimitedListToStringArray</code>
     * 
     * @param str
     *            the String to tokenize
     * @param delimiters
     *            the delimiter characters, assembled as String (each of those
     *            characters is individually considered as delimiter).
     * @return an array of the tokens
     * @see java.util.StringTokenizer
     * @see java.lang.String#trim
     * @see #delimitedListToStringArray
     */
    public static String[] tokenizeToStringArray(String str, String delimiters) {
        if (str == null) {
            return new String[0];
        }
        return tokenizeToStringArray(str, delimiters, true, true);
    }

    /**
     * Tokenize the given String into a String array via a StringTokenizer.
     * <p>
     * The given delimiters string is supposed to consist of any number of
     * delimiter characters. Each of those characters can be used to separate
     * tokens. A delimiter is always a single character; for multi-character
     * delimiters, consider using <code>delimitedListToStringArray</code>
     * 
     * @param str
     *            the String to tokenize
     * @param delimiters
     *            the delimiter characters, assembled as String (each of those
     *            characters is individually considered as delimiter)
     * @param trimTokens
     *            trim the tokens via String's <code>trim</code>
     * @param ignoreEmptyTokens
     *            omit empty tokens from the result array (only applies to
     *            tokens that are empty after trimming; StringTokenizer will not
     *            consider subsequent delimiters as token in the first place).
     * @return an array of the tokens
     * @see java.util.StringTokenizer
     * @see java.lang.String#trim
     * @see #delimitedListToStringArray
     */
    public static String[] tokenizeToStringArray(String str, String delimiters, boolean trimTokens,
                                                 boolean ignoreEmptyTokens) {

        StringTokenizer st = new StringTokenizer(str, delimiters);
        List<String> tokens = InstanceUtil.newArrayList();
        while (st.hasMoreTokens()) {
            String token = st.nextToken();
            if (trimTokens) {
                token = token.trim();
            }
            if (!ignoreEmptyTokens || token.length() > 0) {
                tokens.add(token);
            }
        }
        return toStringArray(tokens);
    }

    /**
     * 转换为字符串,null转换为空字符串
     * 
     * @param object
     * @return
     */
    public static String toString(Object object) {
        if (object == null) {
            return "";
        }
        return object.toString().trim();
    }

    /**
     * Copy the given Collection into a String array. The Collection must
     * contain String elements only.
     * 
     * @param collection
     *            the Collection to copy
     * @return the String array (<code>null</code> if the Collection was
     *         <code>null</code> as well)
     */
    public static String[] toStringArray(Collection<?> collection) {
        if (collection == null) {
            return null;
        }
        return (String[]) collection.toArray(new String[collection.size()]);
    }

    /**
     * Remove null value and " ".
     * 
     * @param value
     * @return value or ""
     */
    public final static String trim(String value) {
        if (value == null) {
            return "";
        }
        return value.trim();
    }

    public static boolean isEmpty(String[] paramArray) {
        for (int i = 0; i < paramArray.length; i++) {
            if (!isEmpty(paramArray[i])) {
                return true;
            }
        }
        return false;
    }

    public static boolean isNotEmpty(String[] paramArray) {
        return !isEmpty(paramArray);
    }

    public static boolean isEmpty(String str) {
        if ("".equals(str) || str == null) {
            return true;
        }
        return false;
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    public static boolean isBlank(String str) {
        if (trim("").equals(str) || isEmpty(str)) {
            return true;
        }
        return false;
    }

    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }

    /**
     * 将字符串转换成ASCII码
     * 
     * @param cnStr
     * @return String
     */
    public static String getCnASCII(String cnStr) {
        StringBuffer strBuf = new StringBuffer();
        // 将字符串转换成字节序列
        byte[] bGBK = cnStr.getBytes();
        for (int i = 0; i < bGBK.length; i++) {
            // System.out.println(Integer.toHexString(bGBK[i] & 0xff));
            // 将每个字符转换成ASCII码
            strBuf.append(Integer.toHexString(bGBK[i] & 0xff));
        }
        return strBuf.toString();
    }

    /**
     * 将货币转换为大写形式(类内部调用)<br>
     * 输入字符串必须正整数，只允许前导空格(必须右对齐)，不宜有前导零
     * 
     * @param val
     * @return String
     */
    private static String PositiveIntegerToHanStr(String NumStr) {
        // 输入字符串必须正整数，只允许前导空格(必须右对齐)，不宜有前导零
        String RMBStr = "";
        boolean lastzero = false;
        boolean hasvalue = false; // 亿、万进位前有数值标记
        int len, n;
        len = NumStr.length();
        if (len > 15)
            return "数值过大!";
        for (int i = len - 1; i >= 0; i--) {
            if (NumStr.charAt(len - i - 1) == ' ')
                continue;
            n = NumStr.charAt(len - i - 1) - '0';
            if (n < 0 || n > 9)
                return "输入含非数字字符!";

            if (n != 0) {
                if (lastzero)
                    RMBStr += HanDigiStr[0]; // 若干零后若跟非零值，只显示一个零
                // 除了亿万前的零不带到后面
                // if( !( n==1 && (i%4)==1 && (lastzero || i==len-1) ) )
                // 如十进位前有零也不发壹音用此行
                if (!(n == 1 && (i % 4) == 1 && i == len - 1)) // 十进位处于第一位不发壹音
                    RMBStr += HanDigiStr[n];
                RMBStr += HanDiviStr[i]; // 非零值后加进位，个位为空
                hasvalue = true; // 置万进位前有值标记
            } else {
                if ((i % 8) == 0 || ((i % 8) == 4 && hasvalue)) // 亿万之间必须有非零值方显示万
                    RMBStr += HanDiviStr[i]; // “亿”或“万”
            }
            if (i % 8 == 0)
                hasvalue = false; // 万进位前有值标记逢亿复位
            lastzero = (n == 0) && (i % 4 != 0);
        }

        if (RMBStr.length() == 0)
            return HanDigiStr[0]; // 输入空字符或"0"，返回"零"
        return RMBStr;
    }
}
