package org.barley.web;

public class PageAjaxResult extends Resonse {
	/**
	 * 当前页
	 */
	private Integer page;

	/**
	 * 总页数
	 */
	private Integer recordsTotal;

	/**
	 * 
	 */
	private Integer recordsFiltered;

	/**
	 * @return 返回 recordsTotal
	 */
	public Integer getRecordsTotal() {
		return recordsTotal;
	}

	/**
	 * @param recordsTotal 设置 recordsTotal
	 */
	public void setRecordsTotal(Integer recordsTotal) {
		this.recordsTotal = recordsTotal;
		this.recordsFiltered = recordsTotal;
	}

	/**
	 * @return 返回 recordsFiltered
	 */
	public Integer getRecordsFiltered() {
		return recordsFiltered;
	}

	/**
	 * @param recordsFiltered 设置 recordsFiltered
	 */
	public void setRecordsFiltered(Integer recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

}
