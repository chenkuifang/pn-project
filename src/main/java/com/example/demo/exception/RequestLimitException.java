package com.example.demo.exception;

/**
 * 限流异常类
 *
 * @author QuiFar
 * @version V1.0
 **/
public class RequestLimitException extends Exception {
    private static final long serialVersionUID = 1L;

    public RequestLimitException() {
        super("HTTP请求超出设定的限制");
    }

    public RequestLimitException(String message) {
        super(message);
    }
}
