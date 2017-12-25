package com.example.demo.exception;

import com.example.demo.common.JsonResult;
import com.example.demo.common.util.JsonResultUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 控制器统一异常处理
 *
 * @author QuiFar
 * @version V1.0
 */
@ControllerAdvice
public class MyExceptionHandler {

    private final static Logger logger = LoggerFactory.getLogger(MyExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public JsonResult handle(Exception e) {
        logger.info("系统异常：" + e.getMessage());
        return JsonResultUtils.jsonResult(e.getMessage());
    }


}
