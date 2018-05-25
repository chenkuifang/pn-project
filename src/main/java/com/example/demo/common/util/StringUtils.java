package com.example.demo.common.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * String工具类，继承apache.commons.lang3.StringUtils
 *
 * @author QuiFar
 * @version V1.0
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
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

        // 判断是否包含非法字符
        char[] chars = obj.toCharArray();
        for (char aChar : chars) {
            if (isChineseByScript(aChar)) {
                return result;
            }
        }

        if (obj.substring(0, 1).equals("[") && obj.lastIndexOf("]") > 0) {
            String tem = obj.substring(1, obj.length() - 1);
            result = tem.replace(" ", "").split(",");
        }
        return result;
    }

    /**
     * 判断是否包含中文标点符号
     *
     * @param c
     * @return
     */
    public static boolean isChineseByScript(char c) {
        Character.UnicodeScript sc = Character.UnicodeScript.of(c);
        return (sc == Character.UnicodeScript.HAN);
    }

//	public static void main(String... args) {
//		String[] aaStrings = string2Array("[1]");
//		for (int i = 0, len = aaStrings.length; i < len; i++) {
//			System.err.println(aaStrings[i]);
//		}
//	}

}
