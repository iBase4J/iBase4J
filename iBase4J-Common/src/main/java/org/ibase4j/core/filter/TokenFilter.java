package org.ibase4j.core.filter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ibase4j.core.support.HttpCode;
import org.ibase4j.core.support.Token;
import org.ibase4j.core.util.DataUtil;
import org.ibase4j.core.util.InstanceUtil;
import org.ibase4j.core.util.TokenUtil;
import org.ibase4j.core.util.WebUtil;

import com.alibaba.fastjson.JSON;

/**
 * @author ShenHuaJie
 * @since 2017年3月19日 上午10:21:59
 */
public class TokenFilter implements Filter {
	private static final Logger logger = LogManager.getLogger(TokenFilter.class);

	// 白名单
	private List<String> whiteUrls;

	private int _size = 0;

	public void init(FilterConfig config) throws ServletException {
		// 读取文件
		String path = CsrfFilter.class.getResource("/").getFile();
		whiteUrls = readFile(path + "tokenWhite.txt");
		_size = null == whiteUrls ? 0 : whiteUrls.size();
	}

	public void doFilter(ServletRequest servletRequest, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		if (isWhiteReq(request.getRequestURI())) {
			chain.doFilter(request, response);
		} else {
			String uuid = request.getHeader("UUID");
			if (StringUtils.isNotBlank(uuid)) {
				try {
					Token tokenInfo = TokenUtil.getTokenInfo(uuid);
					if (tokenInfo != null) {
						Long now = System.currentTimeMillis();
						if (now - tokenInfo.getTime() < 1000 * 60 * 30) {
							String value = tokenInfo.getValue();
							TokenUtil.setTokenInfo(uuid, value);
							WebUtil.saveCurrentUser(request, value);
						}
					}
				} catch (Exception e) {
					logger.error("token检查发生异常:", e);
				}
			}
			// 响应
			if (DataUtil.isEmpty(WebUtil.getCurrentUser(request))) {
				response.setContentType("text/html; charset=UTF-8");
				Map<String, Object> modelMap = InstanceUtil.newLinkedHashMap();
				modelMap.put("httpCode", HttpCode.UNAUTHORIZED.value());
				modelMap.put("msg", HttpCode.UNAUTHORIZED.msg());
				modelMap.put("timestamp", System.currentTimeMillis());
				PrintWriter out = response.getWriter();
				out.println(JSON.toJSONString(modelMap));
				out.flush();
				out.close();
			} else {
				chain.doFilter(request, response);
			}
		}
	}

	public void destroy() {
	}

	/*
	 * 判断是否是白名单
	 */
	private boolean isWhiteReq(String requestUrl) {
		if (_size == 0) {
			return true;
		} else {
			for (String urlTemp : whiteUrls) {
				if (requestUrl.indexOf(urlTemp.toLowerCase()) > -1) {
					return true;
				}
			}
		}

		return false;
	}

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
}
