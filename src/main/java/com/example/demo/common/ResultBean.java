package com.example.demo.common;

import java.io.Serializable;
import java.util.List;

/**
 * 分页列表返回的结果集bean
 *
 * @author QuiFar
 * @version V1.0
 */
public class ResultBean extends R {
    /**
     * 列表总数，供分页使用
     */
    private Integer count;
    /**
     * 数据列表
     */
    private List<?> data;

    // 初始值,禁止再为code、msg 赋值
    private ResultBean() {
        super.setCode("0");
        super.setMsg("ok");
    }

    private static ResultBean resultBean = null;

    /***
     * 获取单例(双重检查锁定) 懒汉单例
     *
     * @return
     */
    public static ResultBean getInstance() {
        if (resultBean == null) {
            synchronized (ResultBean.class) {
                if (resultBean == null) {
                    resultBean = new ResultBean();
                }
            }
        }
        return resultBean;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }

    /**
     * 请不要再次赋值
     *
     * @param code
     */
    @Override
    @Deprecated
    public void setCode(String code) {
        throw new AssertionError("Please don't set value to code again");
    }

    /**
     * 请不要再次赋值
     *
     * @param msg
     */
    @Override
    @Deprecated
    public void setMsg(String msg) {
        throw new AssertionError("Please don't set value to msg again");
    }

    @Override
    public String toString() {
        return "ResultBean [code=" + super.getCode() + ", msg=" + super.getMsg() + ", count=" + count + ", data=" + data + "]";
    }

}
