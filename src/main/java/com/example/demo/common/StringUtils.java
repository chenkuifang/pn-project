package com.example.demo.common;

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
}
