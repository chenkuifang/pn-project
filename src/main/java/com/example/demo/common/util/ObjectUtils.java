package com.example.demo.common.util;


/**
 * Object 帮助类
 * <p>
 * 继承 org.apache.commons.lang3的ObjectUtils类
 * </p>
 *
 * @author QuiFar
 */
public class ObjectUtils extends org.apache.commons.lang3.ObjectUtils {

    private ObjectUtils() {
    }

    /**
     * object 类型转doublel类型
     * 如果参数为null 或转换出异常均返回0
     *
     * @param obj
     * @return
     */
    public static double parseDouble(Object obj) {
        double ret = 0D;

        if (obj == null) {
            return ret;
        }

        if (obj instanceof Double) {
            ret = (Double) obj;
        } else {
            try {
                ret = Double.parseDouble(obj.toString());
            } catch (NumberFormatException e) {
                return 0D;
            }
        }
        return ret;

    }
}
