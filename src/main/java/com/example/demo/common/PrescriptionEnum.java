package com.example.demo.common;

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

    private String name;
    private int intVal;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIntVal() {
        return intVal;
    }

    public void setIntVal(int intVal) {
        this.intVal = intVal;
    }

    PrescriptionEnum(String name, int intVal) {
        this.name = name;
        this.intVal = intVal;
    }

}
