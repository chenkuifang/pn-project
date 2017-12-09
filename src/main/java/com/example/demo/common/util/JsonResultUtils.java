package com.example.demo.common.util;

import com.example.demo.common.Constants;
import com.example.demo.common.JsonResult;

import java.util.List;

/**
 * 返回结果工具类
 * <p>
 * 说明:本系统限定控制类的返回类型为String(视图名称)、JsonResult；
 * 为了系统的规范和维护简单，套路一致性，不建议其他的返回类型,个别特殊的除外。
 *
 * @author QuiFar
 * @version V1.0
 */
public class JsonResultUtils {

	private JsonResultUtils() {
	}

	/**
	 * 返回普通json结果,只有code和msg属性值
	 *
	 * @param flag
	 *            判定是否成功的标志, >0 表示成功
	 * @return
	 */
	public static JsonResult jsonResult(int flag) {
		JsonResult r = JsonResult.getInstance();
		// 结果返回
		if (flag > 0) {
			r.setCode(Constants.SUCCESS_CODE);
			r.setMsg(Constants.SUCCESS_DESCRIPTION);
		} else {
			r.setCode(Constants.FAIL_CODE);
			r.setMsg(Constants.FAIL_DESCRIPTION);
		}
		return r;
	}

	/**
	 * 返回普通json结果,有code,msg,data属性值
	 *
	 * @param data
	 *            数据
	 * @return
	 */
	public static JsonResult jsonResult(List<?> data) {
		JsonResult r = JsonResult.getInstance();
		r.setData(data);
		return r;
	}

	/**
	 * 返回需要翻页的json结果集
	 *
	 * @param data
	 *            数据
	 * @return
	 */
	public static JsonResult jsonResult(List<?> data, int flag) {
		JsonResult r = JsonResult.getInstance();
		r.setData(data);
		return r;
	}

	/**
	 * 返回需要翻页的json结果集,包含code,msg,data,count属性
	 *
	 * @param data
	 *            总数据
	 * @param countPage
	 *            总页数
	 * @return
	 */
	public static JsonResult jsonPageResult(List<?> data, int countPage) {
		JsonResult r = JsonResult.getInstance();
		r.setCode("0");
		r.setMsg("ok");
		r.setCount(countPage);
		r.setData(data);
		return r;
	}

}
