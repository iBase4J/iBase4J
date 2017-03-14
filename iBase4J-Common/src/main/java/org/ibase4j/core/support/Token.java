package org.ibase4j.core.support;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Token implements Serializable {
	private String value;
	private Long time;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Long getTime() {
		return time;
	}

	public void setTime(Long time) {
		this.time = time;
	}
}
