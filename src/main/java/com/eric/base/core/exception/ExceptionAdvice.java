package com.eric.base.core.exception;

import com.eric.base.core.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 描述:统一异常处理
 *
 * @author eric
 * @create 2018-06-20 下午5:46
 */
@ControllerAdvice
@ResponseBody
public class ExceptionAdvice {
    private Logger logger = LoggerFactory.getLogger(ExceptionAdvice.class);

    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public JsonResult handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        logger.error("参数解析失败", e);
        return new JsonResult().fail("could_not_read_json");

    }


    /**
     * 405 - Method Not Allowed
     */
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public JsonResult handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        logger.error("不支持当前请求方法", e);
        return new JsonResult().fail("request_method_not_supported");

    }


    /**
     * 415 - Unsupported Media Type
     */
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public JsonResult handleHttpMediaTypeNotSupportedException(Exception e) {
        logger.error("不支持当前媒体类型", e);
        return new JsonResult().fail("content_type_not_supported");

    }


    /**
     * 400 - Parameter Validate Exception
     */

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public JsonResult handleValidationException(BindException e) {
        logger.error("参数验证失败", e);
        // 获取验证失败参数 默认提示信息
        BindingResult bindingResult = e.getBindingResult();
        String errorMessage = null;
        if (bindingResult.hasErrors()) {
            errorMessage = bindingResult.getFieldError().getDefaultMessage();
        }
        if (!StringUtils.isEmpty(errorMessage)) {
            return new JsonResult().fail(errorMessage);
        }
        return new JsonResult().fail("参数验证失败，请确认");
    }

    /**
     * 500 - Internal Server Error
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public JsonResult handleException(Exception e) {
        logger.error("服务运行异常", e);
        return new JsonResult().fail(e.getMessage());
    }

}
