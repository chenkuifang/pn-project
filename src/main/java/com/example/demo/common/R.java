package com.example.demo.common;

import java.io.Serializable;

/**
 * @Description:异步返回的结果类,为了规范,必须使用
 * @author QuiFar
 * @date 2017年11月30日 上午20:02:28
 * @version V1.0
 */
public class R implements Serializable {
	private static final long serialVersionUID = 6071637847323350417L;

	private String code;
	private String msg;

	private static R resultBean = null;

	/***
	 * 获取单例(双重检查锁定) 懒汉单例
	 * 
	 * @return
	 */
	public static R getInstance() {
		if (resultBean == null) {
			synchronized (R.class) {
				if (resultBean == null) {
					resultBean = new R();
				}
			}
		}
		return resultBean;
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

	@Override
	public String toString() {
		return "ResultBean [code=" + code + ", msg=" + msg + "]";
	}

}
