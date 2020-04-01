package com.example.demo.exception;

import com.example.demo.common.JsonResult;
import com.example.demo.common.util.JsonResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 控制器统一异常处理
 *
 * @author QuiFar
 * @version V1.0
 */
@ControllerAdvice
@Slf4j
public class MyExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public JsonResult handle(Exception e) {
        log.info("系统异常：" + e.getMessage());
        return JsonResultUtils.jsonResult(e.getMessage());
    }


}
