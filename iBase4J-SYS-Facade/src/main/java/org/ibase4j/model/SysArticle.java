package org.ibase4j.model;


import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import top.ibase4j.core.base.BaseModel;


/**
 * <p>
 * 文章
 * </p>
 *
 * @author ShenHuaJie
 * @since 2017-03-12
 */
@TableName("sys_article")
public class SysArticle extends BaseModel {

    private static final long serialVersionUID = 1L;

    /**
     * 类型
     */
	@TableField("type_")
	private String type;
    /**
     * 作者
     */
	@TableField("author_")
	private String author;
    /**
     * 标题
     */
	@TableField("title_")
	private String title;
    /**
     * 内容
     */
	@TableField("content_")
	private String content;
    /**
     * 外部链接
     */
	@TableField("out_url")
	private String outUrl;
    /**
     * seo关键字
     */
	@TableField("seo_keyword")
	private String seoKeyword;
    /**
     * seo描述
     */
	@TableField("seo_description")
	private String seoDescription;
    /**
     * 是否置顶
     */
	@TableField("is_top")
	private Integer isTop;


	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getOutUrl() {
		return outUrl;
	}

	public void setOutUrl(String outUrl) {
		this.outUrl = outUrl;
	}

	public String getSeoKeyword() {
		return seoKeyword;
	}

	public void setSeoKeyword(String seoKeyword) {
		this.seoKeyword = seoKeyword;
	}

	public String getSeoDescription() {
		return seoDescription;
	}

	public void setSeoDescription(String seoDescription) {
		this.seoDescription = seoDescription;
	}

	public Integer getIsTop() {
		return isTop;
	}

	public void setIsTop(Integer isTop) {
		this.isTop = isTop;
	}

}