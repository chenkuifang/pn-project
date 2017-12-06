package com.example.demo.common.util;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description: String工具类，继承apache.commons.lang3.StringUtils
 * @author QuiFar
 * @date 2017年11月11日 下午10:28:35
 * @version V1.0
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {

	/**
	 * 判断是否是有效手机号码
	 * 
	 * @param mobiles
	 * @return
	 */
	public static boolean isMobileNO(String mobiles) {
		Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(17[0-9])|(18[0,5-9]))\\d{8}$");
		Matcher m = p.matcher(mobiles);
		return m.matches();
	}

	/**
	 * 返回int类型，如果参数为null或转换出异常时则返回 0
	 * 
	 * @param o
	 * @return
	 */
	public static int parseInteger(Object o) {
		int reuslt = 0;
		if (Objects.isNull(o)) {
			return reuslt;
		}

		try {
			reuslt = Integer.parseInt(o.toString());
		} catch (Exception e) {
			reuslt = 0;
		}
		return reuslt;
	}

	/**
	 * 根据数组字符串转为数组,如果格式不对返回空数组
	 * <p>
	 * 格式如下：[10001,10002,10003,10004,10008]
	 * </p>
	 * 
	 * @param obj
	 * @return
	 */
	public static String[] string2Array(String obj) {
		String result[] = {};
		if (isEmpty(obj)) {
			return result;
		}

		if (obj.substring(0, 1).equals("[") && obj.lastIndexOf("]") > 0) {
			String tem = obj.substring(1, obj.length() - 1);
			result = tem.split(",");
		}
		return result;
	}

//	public static void main(String... args) {
//		String[] aaStrings = string2Array("[1]");
//		for (int i = 0, len = aaStrings.length; i < len; i++) {
//			System.err.println(aaStrings[i]);
//		}
//	}

}
