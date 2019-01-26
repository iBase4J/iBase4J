package org.ibase4j.model;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import top.ibase4j.core.base.BaseModel;

/**
 * <p>
 * 通知公告表
 * </p>
 *
 * @author ShenHuaJie
 * @since 2017-01-29
 */
@SuppressWarnings("serial")
@TableName("sys_notice")
public class SysNotice extends BaseModel {
	/**
	 * 公告标题
	 */
	@TableField(value="notice_title")
	private String noticeTitle;
	/**
	 * 公告类型
	 */
	@TableField(value="notice_type")
	private String noticeType;
	/**
	 * 发布时间
	 */
	@TableField(value="send_time")
	private Date sendTime;
	/**
	 * 信息来源
	 */
	@TableField(value="info_sources")
	private String infoSources;
	/**
	 * 来源地址
	 */
	@TableField(value="sources_url")
	private String sourcesUrl;
	/**
	 * 阅读次数
	 */
	@TableField(value = "reader_times")
	private Integer readerTimes;
	/**
	 * 发布状态
	 */
	@TableField(value = "status_")
	private String status;
	/**
	 * 内容
	 */
	@TableField(value="content_")
	private String content;


	public String getNoticeTitle() {
		return noticeTitle;
	}

	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}

	public String getNoticeType() {
		return noticeType;
	}

	public void setNoticeType(String noticeType) {
		this.noticeType = noticeType;
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public String getInfoSources() {
		return infoSources;
	}

	public void setInfoSources(String infoSources) {
		this.infoSources = infoSources;
	}

	public String getSourcesUrl() {
		return sourcesUrl;
	}

	public void setSourcesUrl(String sourcesUrl) {
		this.sourcesUrl = sourcesUrl;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getReaderTimes() {
		return readerTimes;
	}

	public void setReaderTimes(Integer readerTimes) {
		this.readerTimes = readerTimes;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	protected Serializable pkVal() {
		return getId();
	}

}
