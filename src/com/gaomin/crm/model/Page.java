package com.gaomin.crm.model;

import java.util.List;

/**
 * 封装分页信息
 * 
 * 
 * @author GYX09
 * @data 2016年7月13日上午10:35:43
 * @param <T>
 */
public class Page<T> {

	public static final int NORMAL_PAGESIZE = 10;
	public static final int SMALL_PAGESIZE = 5;
	public static final int BIG_PAGESIZE = 15;
	public static final int HUGE_PAGESIZE = 20;

	private int pageNo;
	private int pageSize;

	private int totalElements;
	private int totalPageNo;
	private List<T> content;

	public Page(int pageNo, int pageSize, int totalElements) {
		this.pageNo = pageNo;
		this.pageSize = pageSize;
		this.totalElements = totalElements;

		// 通过总记录数和每页显示记录数计算总页数
		this.totalPageNo = this.totalElements / this.pageSize
				+ ((this.totalElements % this.pageSize) == 0 ? 0 : 1);

		if (this.pageNo > this.totalPageNo) {
			this.pageNo = this.totalPageNo;
		}

		if (this.pageNo < 1) {
			this.pageNo = 1;
		}
	}

	public List<T> getContent() {
		return content;
	}

	public void setContent(List<T> content) {
		this.content = content;
	}

	public int getPageNo() {
		return pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public int getTotalElements() {
		return totalElements;
	}

	public int getTotalPageNo() {
		return totalPageNo;
	}

	public boolean isHasPrev() {
		return this.pageNo > 1;
	}

	public int getPrevPageNo() {
		if (isHasPrev()) {
			return this.pageNo - 1;
		}
		return this.pageNo;
	}

	public boolean isHasNext() {
		return this.pageNo < this.totalPageNo;
	}

	public int getNextPageNo() {
		if (isHasNext()) {
			return this.pageNo + 1;
		}
		return this.pageNo;
	}

}
