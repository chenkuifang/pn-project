package com.example.demo.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 结果返回类
 *
 * @author QuiFar
 * @version V1.0
 */
public class JsonResult implements Serializable {

	private static final long serialVersionUID = -4908915966053299827L;
	/**
	 * 编码
	 */
	private String code;
	/**
	 * 消息
	 */
	private String msg;
	/**
	 * 列表总数，供分页使用
	 */
	private Integer count;
	/**
	 * 数据列表
	 */
	private List<?> data;

	// 初始值,禁止再为code、msg 赋值
	private JsonResult() {
	}

	private static JsonResult resultBean = null;

	private static List<?> list = new ArrayList<>();

	/***
	 * 获取单例(双重检查锁定) 懒汉单例
	 *
	 * @return
	 */
	public static JsonResult getInstance() {
		if (resultBean == null) {
			synchronized (JsonResult.class) {
				if (resultBean == null) {
					resultBean = new JsonResult();
				}
			}
		}

		clear();

		return resultBean;
	}

	/**
	 * 初始化对象
	 */
	private static void clear() {
		if (list == null) {
			list = new ArrayList<>();
		} else {
			list.clear();
		}
		resultBean.setCode("");
		resultBean.setMsg("");
		resultBean.setCount(0);
		resultBean.setData(list);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
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
