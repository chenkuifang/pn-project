package com.example.demo.common.util;

import com.example.demo.common.Constants;
import com.example.demo.common.R;
import com.example.demo.common.ResultBean;

import java.util.List;


/**
 * 返回结果工具类
 * <p>
 * 说明:本系统限定控制类的返回类型为String(视图名称)、R(普通Json)、ResultBean(带翻页的Json)；
 * 为了系统的规范和维护简单，套路一致性，不建议其他的返回类型。
 *
 * @author QuiFar
 * @version V1.0
 */
public class ResultUtils {

    private ResultUtils() {
    }

    /**
     * 返回普通json结果
     *
     * @param flag 判定是否成功的标志, >0 表示成功
     * @return
     */
    public static R jsonResult(int flag) {
        R r = R.getInstance();
        // 结果返回
        if (flag > 0) {
            r.setCode(Constants.SUCCESS_CODE);
            r.setMsg(Constants.SUCCESS_DESCRIPTION);
        } else {
            r.setCode(Constants.FAIL_CODE);
            r.setMsg(Constants.FAIL_DESCRIPTION);
        }
        return r;
    }

    /**
     * 返回需要翻页的json结果集
     *
     * @param data      总数据
     * @param countPage 总页数
     * @return
     */
    public static ResultBean jsonPageResult(List<?> data, int countPage) {
        ResultBean resultBean = ResultBean.getInstance();
        resultBean.setCount(countPage);
        resultBean.setData(data);
        return resultBean;
    }

}
