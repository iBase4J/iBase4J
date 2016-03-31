package org.shjr.iplat.core;

/**
 * 分页类
 * 
 * @date 2015-4-29
 */
public class PageBean {

	private Integer pageIndex; // 分页索引，从0开始
	private Integer pageLimit; // 每页显示记录数
	private Integer pageStart; // 每次取数据时的起始位置，根据pageIndex和pageLimit计算出
	private Integer rowCount; // 总记录数
	private Integer pageCount; // 总页数，根据rowCount和pageLimit计算出
	private Integer showCount; // 显示记录数, 根据rowCount,pageIndex和pageLimit计算出

	public PageBean() {

	}

	public PageBean(int pageLimit) {
		this(1, pageLimit);
	}

	public PageBean(int pageIndex, int pageLimit) {
		this.pageIndex = pageIndex;
		this.pageLimit = pageLimit;
	}

	public Integer getPageIndex() {
		if (pageIndex == null) {
			pageIndex = 1;
		}
		return pageIndex;
	}

	public PageBean setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
		return this;
	}

	public Integer getPageLimit() {
		if (pageLimit == null) {
			pageLimit = 10;
		}
		return pageLimit;
	}

	public PageBean setPageLimit(Integer pageLimit) {
		this.pageLimit = pageLimit;
		return this;
	}

	/**
	 * 如果未指定pageStart的值，将根据pageIndex和pageLimit自动计算得出值
	 * 
	 * @return
	 * @date 2014-3-12 下午12:54:55
	 */
	public Integer getPageStart() {
		if (getPageIndex() != null && pageIndex > 0 && getPageLimit() != null && pageLimit > 0) {
			pageStart = (pageIndex - 1) * pageLimit;
		}
		return pageStart;
	}

	public PageBean setPageStart(Integer pageStart) {
		this.pageStart = pageStart;
		return this;
	}

	public Integer getRowCount() {
		return rowCount;
	}

	public PageBean setRowCount(Integer rowCount) {
		this.rowCount = rowCount;
		return this;
	}

	/**
	 * 如果未指定pageCount值，将根据pageLimit和rowCount计算得出值
	 * 
	 * @return
	 * @date 2014-3-12 下午12:55:42
	 */
	public Integer getPageCount() {
		if (getPageLimit() != null && pageLimit > 0 && rowCount != null && rowCount > 0) {
			pageCount = (rowCount - 1) / pageLimit + 1;
		}
		return pageCount;
	}

	public PageBean setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
		return this;
	}

	public Integer getShowCount() {
		this.showCount = this.pageLimit;
		if (pageIndex == getPageCount() && (rowCount % pageLimit != 0 || rowCount == 0)) {
			this.showCount = rowCount % pageLimit;
		}
		return this.showCount;
	}
}
