package com.example.demo.common;

/**
 * 系统枚举类
 *
 * @author QuiFar
 **/
public enum SystemEnum {

    UPDATE("update", "修改"), DELETE("delete", "删除"), CREATE("create", "新增");

    SystemEnum(String key, String value) {
        this.key = key;
        this.value = value;
    }

    private final String key;
    private final String value;

    @Override
    public String toString() {
        return "SystemEnum{" + "key='" + key + '\'' + ", value='" + value + '\'' + '}';
    }
}
