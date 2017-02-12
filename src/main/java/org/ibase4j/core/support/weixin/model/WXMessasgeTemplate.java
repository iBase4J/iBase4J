package org.ibase4j.core.support.weixin.model;

/**
 * @author ShenHuaJie
 * @since 2017年2月3日 下午5:08:21
 */
public class WXMessasgeTemplate {
	private String touser;
	private String template_id;
	private String url;
	private Object data;

	public String getTouser() {
		return touser;
	}

	public void setTouser(String touser) {
		this.touser = touser;
	}

	public String getTemplate_id() {
		return template_id;
	}

	public void setTemplate_id(String template_id) {
		this.template_id = template_id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
