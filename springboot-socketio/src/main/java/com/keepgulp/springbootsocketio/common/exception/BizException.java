package com.keepgulp.springbootsocketio.common.exception;

/**
 * @description: 自定义异常
 * @author: guodongqing
 * @create: 2018-06-27 11:14
 **/
public class BizException extends RuntimeException {
    private static final long serialVersionUID = -2466703721851641645L;

    public BizException() {
    }

    public BizException(String message) {
        super(message);
    }

    public BizException(String message, Throwable cause) {
        super(message, cause);
    }
}
