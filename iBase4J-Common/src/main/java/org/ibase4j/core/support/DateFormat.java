package org.ibase4j.core.support;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.ibase4j.core.util.DateUtil;


@SuppressWarnings("serial")
public class DateFormat extends SimpleDateFormat {
	
	public Date parse(String source) throws ParseException {
		return DateUtil.stringToDate(source);
	}
}
