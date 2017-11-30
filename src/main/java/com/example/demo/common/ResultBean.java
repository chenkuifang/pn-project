package com.example.demo.common;

import java.io.Serializable;
import java.util.List;

/**
 * @Description:分页列表返回的结果集bean
 * @author QuiFar
 * @date 2017年11月30日 上午20:02:28
 * @version V1.0
 */
public class ResultBean implements Serializable {
	private static final long serialVersionUID = 6071637847323350417L;

	// 以下是lay-table 返回必须的参数
	private String code;
	private String msg;
	/** 列表总数，供分页使用 */
	private Integer count;
	/** 数据列表 */
	private List<?> data;

	// 初始值,禁止再为code、msg 赋值,如需赋值，加上set方法即可
	private ResultBean() {
		code = "0";
		msg = "ok";
	}
	private static ResultBean resultBean = null;

	/***
	 * 获取单例(双重检查锁定) 懒汉单例
	 * 
	 * @return
	 */
	public static ResultBean getInstance() {
		if (resultBean == null) {
			synchronized (ResultBean.class) {
				if (resultBean == null) {
					resultBean = new ResultBean();
				}
			}
		}
		return resultBean;
	}

	public String getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public List<?> getData() {
		return data;
	}

	public void setData(List<?> data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "ResultBean [code=" + code + ", msg=" + msg + ", count=" + count + ", data=" + data + "]";
	}

}
