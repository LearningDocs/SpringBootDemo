package com.keepgulp.springbootsocketio.common.exception;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;

import com.keepgulp.springbootsocketio.common.response.BaseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @description: 统一异常处理
 * @author: guodongqing
 * @create: 2018-06-27 11:13
 **/
@ControllerAdvice
public class GlobalExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(value = BizException.class)
    @ResponseBody
    public BaseResult<Object> defaultExceptionHandle(HttpServletRequest request, Exception e)
            throws IOException {
        logger.error("访问" + request.getRequestURI() + " 发生错误, 错误信息:" + e.getMessage());
        BaseResult<Object> result = new BaseResult<Object>(false, e.getMessage());
        return result;
    }
}
