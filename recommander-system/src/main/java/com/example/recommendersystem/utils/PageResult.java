package com.example.recommendersystem.utils;

public class PageResult {
	private int pageIndex;//索引页
	private int pageSize;//每页展示的数量
	private int count;//条数
	private int maxRow;//最大页数
	private Object resultList;//结果集

	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getMaxRow() {
		return maxRow;
	}
	public void setMaxRow(int maxRow) {
		this.maxRow = maxRow;
	}
	public Object getResultList() {
		return resultList;
	}
	public void setResultList(Object resultList) {
		this.resultList = resultList;
	}

}
