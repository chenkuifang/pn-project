package com.example.demo.common;

import java.util.Map;

/**
 * @Description: 请求参数类
 * @author QuiFar
 * @date 2017年11月30日 上午11:02:28
 * @version V1.0
 */
public class MyRequestParams {

	/** 当前页 */
	private Integer page;
	/** 每页显示条数 */
	private Integer limit;
	/** 其他参数封装 */
	private Map<String, String> data;

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public Map<String, String> getData() {
		return data;
	}

	public void setData(Map<String, String> data) {
		this.data = data;
	}

}
