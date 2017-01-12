package org.ibase4j.core.filter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 
 * 非法字符过滤器（防SQL注入，防XSS漏洞）
 * 
 * 
 */
public class XssFilter implements Filter {
	private static final Logger logger = LogManager.getLogger(XssFilter.class);

	/**
	 * 排除部分URL不做过滤
	 */
	private List<String> excludeUrls = new ArrayList<String>();

	/**
	 * 公告新增、修改用到富文本，对标签进行转义
	 */
	private List<String> noticeUrls = new ArrayList<String>();

	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;
		String pathInfo = req.getPathInfo() == null ? "" : req.getPathInfo();
		String url = req.getServletPath() + pathInfo;
		String uri = req.getRequestURI();
		boolean isNoticeUrl = false;
		// 排除部分URL不做过滤。
		for (String str : excludeUrls) {
			if (uri.indexOf(str) >= 0) {
				logger.info("该URL不作校验：" + url);
				arg2.doFilter(req, response);
				return;
			}
		}
		for (String st : noticeUrls) {
			if (uri.indexOf(st) >= 0) {
				isNoticeUrl = true;
				break;
			}
		}
		// 获取请求所有参数，校验防止SQL注入，防止XSS漏洞

		Enumeration<?> params = req.getParameterNames();
		String paramN = null;
		while (params.hasMoreElements()) {
			paramN = (String) params.nextElement();
			String paramVale = req.getParameter(paramN);
			logger.debug(paramN + "==" + paramVale);
			if (isNoticeUrl) {
				paramVale = xssEncode(paramVale);
			}
			// 校验是否存在SQL注入信息
			if (checkSQLInject(paramVale, url)) {
				errorResponse(response, paramN);
				return;
			}
		}
		arg2.doFilter(req, response);

	}

	private void errorResponse(HttpServletResponse response, String paramNm) throws IOException {
		String warning = "输入项中不能包含非法字符。";

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		out.println("{\"httpCode\":\"-9998\",\"msg\":\"" + warning + "\", \"fieldName\": \"" + paramNm + "\"}");
		out.flush();
		out.close();
	}

	public void destroy() {
	}

	public void init(FilterConfig filterconfig1) throws ServletException {
		// 读取文件
		String path = XssFilter.class.getResource("/").getFile();
		excludeUrls = readFile(path + "xssWhite.txt");
		noticeUrls.add("notice!saveNotice");
		noticeUrls.add("notice!updateNoticeById");
	}

	/**
	 * 读取白名单
	 * 
	 * @param fileName
	 * @return
	 */
	private List<String> readFile(String fileName) {
		List<String> list = new ArrayList<String>();
		BufferedReader reader = null;
		FileInputStream fis = null;
		try {
			File f = new File(fileName);
			if (f.isFile() && f.exists()) {
				fis = new FileInputStream(f);
				reader = new BufferedReader(new InputStreamReader(fis, "UTF-8"));
				String line;
				while ((line = reader.readLine()) != null) {
					if (!"".equals(line)) {
						list.add(line);
					}
				}
			}
		} catch (Exception e) {
			logger.error("readFile", e);
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
			} catch (IOException e) {
				logger.error("InputStream关闭异常", e);
			}
			try {
				if (fis != null) {
					fis.close();
				}
			} catch (IOException e) {
				logger.error("FileInputStream关闭异常", e);
			}
		}
		return list;
	}

	private String xssEncode(String s) {
		if (s == null || s.isEmpty()) {
			return s;
		}
		StringBuilder sb = new StringBuilder(s.length() + 16);
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			switch (c) {
			case '>':
				sb.append('＞');// 全角大于号
				break;
			case '<':
				sb.append('＜');// 全角小于号
				break;
			case '\'':
				sb.append('‘');// 全角单引号
				break;
			case '\"':
				sb.append('“');// 全角双引号
				break;
			case '&':
				sb.append('＆');// 全角
				break;
			case '\\':
				sb.append('＼');// 全角斜线
				break;
			case '#':
				sb.append('＃');// 全角井号
				break;
			case '(':
				sb.append('（');//
				break;
			case ')':
				sb.append('）');//
				break;
			default:
				sb.append(c);
				break;
			}
		}
		return sb.toString();
	}

	/**
	 * 
	 * 检查是否存在非法字符，防止SQL注入
	 * 
	 * @param str
	 *            被检查的字符串
	 * @return ture-字符串中存在非法字符，false-不存在非法字符
	 */
	public static boolean checkSQLInject(String str, String url) {
		if (StringUtils.isEmpty(str)) {
			return false;// 如果传入空串则认为不存在非法字符
		}

		// 判断黑名单
		String[] inj_stra = { "script", "mid", "master", "truncate", "insert", "select", "delete", "update", "declare",
				"iframe", "'", "onreadystatechange", "alert", "atestu", "xss", ";", "'", "\"", "<", ">", "(", ")", ",",
				"\\", "svg", "confirm", "prompt", "onload", "onmouseover", "onfocus", "onerror" };

		str = str.toLowerCase(); // sql不区分大小写

		for (int i = 0; i < inj_stra.length; i++) {
			if (str.indexOf(inj_stra[i]) >= 0) {
				logger.info("xss防攻击拦截url:" + url + "，原因：特殊字符，传入str=" + str + ",包含特殊字符：" + inj_stra[i]);
				return true;
			}
		}
		return false;
	}
}
