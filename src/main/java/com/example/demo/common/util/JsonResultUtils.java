package com.example.demo.common.util;

import com.example.demo.common.Constants;
import com.example.demo.common.JsonResult;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

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
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class JsonResultUtils {

    /**
     * 返回普通json结果,只有code和msg属性值,返回："成功" 或者 "失败"
     *
     * @param flag 判定是否成功的标志, >0 表示成功
     * @return
     */
    public static JsonResult jsonResult(int flag) {
        // 结果返回
        if (flag > 0) {
            return jsonResult(Constants.SUCCESS_CODE, Constants.SUCCESS_DESCRIPTION);
        }
        return jsonResult(Constants.FAIL_DESCRIPTION);
    }

    /**
     * 返回成功数据，有效属性：code,msg,data
     *
     * @param data 数据
     * @return
     */
    public static JsonResult jsonResult(List<?> data) {
        return jsonResult(data, 1);
    }

    /**
     * 返回普通json结果,有code,msg,data属性值，
     *
     * @param data 数据
     * @param flag 判定是否成功的标志, >0 表示成功
     * @return 返回："成功" 或者 "失败" + 数据
     */
    public static JsonResult jsonResult(List<?> data, int flag) {
        // 结果返回
        if (flag > 0) {
            return jsonResult(Constants.SUCCESS_CODE, Constants.SUCCESS_DESCRIPTION, data, 0);
        }

        return jsonResult(Constants.FAIL_CODE, Constants.FAIL_DESCRIPTION, data, 0);
    }

    /**
     * 返回错误信息结果,包含有效属性：code,msg
     *
     * @param msg 返回的信息
     * @return
     */
    public static JsonResult jsonResult(String msg) {
        return jsonResultNoData(Constants.FAIL_CODE, msg);
    }

    /**
     * 根据参数返回普通json结果,包含有效属性：code,msg
     *
     * @param code 自定义返回编码
     * @param msg  自定义返回消息
     * @return
     */
    public static JsonResult jsonResult(String code, String msg) {
        return jsonResultNoData(code, msg);

    }

    /**
     * 返回需要翻页的json结果集,包含有效属性：code,msg,data,count
     *
     * @param data      总数据
     * @param countPage 总页数
     * @return
     */
    public static JsonResult jsonPageResult(List<?> data, int countPage) {
        return jsonResult("0", "ok", data, countPage);
    }

    /**
     * 需要数据返回时用(一般供列表使用)
     *
     * @param code      返回码
     * @param msg       返回消息
     * @param data      返回数据
     * @param countPage 返回数据的总数
     * @return
     */
    private static JsonResult jsonResult(String code, String msg, List<?> data, int countPage) {
        //JsonResult r = JsonResult.getInstance();
        JsonResult r = new JsonResult();
        r.setCode(code);
        r.setMsg(msg);
        r.setCount(countPage);
        r.setData(data);
        return r;
    }

    /**
     * 不需要数据返回时用
     *
     * @param code 返回码
     * @param msg  返回消息
     * @return
     */
    private static JsonResult jsonResultNoData(String code, String msg) {
        JsonResult r = new JsonResult();
        r.setCode(code);
        r.setMsg(msg);
        return r;
    }

}
