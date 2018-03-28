package com.example.demo.common;

import lombok.Getter;
import lombok.Setter;

/**
 * 处方类型枚举类
 *
 * @author QuiFar
 **/
public enum PrescriptionEnum {

    CHINESE_MEDICINE("中药", 0),
    WESTERN_MEDICINE("西药", 1),
    PASTE_PRESCRIPTION("膏方", 2),
    PILL("丸剂", 3),
    EXCEPTION_TYPE("类型异常", 4),
    POWDER("散剂", 5),
    SOUP_PACKAGE("汤包", 6);

    @Setter
    @Getter
    private String name;
    @Setter
    @Getter
    private int intVal;


    PrescriptionEnum(String name, int intVal) {
        this.name = name;
        this.intVal = intVal;
    }

}
