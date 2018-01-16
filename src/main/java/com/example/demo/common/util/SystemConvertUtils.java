package com.example.demo.common.util;


import com.example.demo.common.PrescriptionEnum;

/**
 * 系统值转换工具类
 *
 * @author QuiFar
 * @version V1.0
 **/
public class SystemConvertUtils {
    private SystemConvertUtils() {
    }

    /**
     * 处方类型转换
     *
     * @param prescriptionType 处方类型数据库值
     * @return 对应的str
     */
    public static String convertPrescriptionType(int prescriptionType) {
        String value = "未知";

        for (PrescriptionEnum obj : PrescriptionEnum.values())
            if (prescriptionType == obj.getIntVal())
                return obj.getName();

        return value;
    }
}
