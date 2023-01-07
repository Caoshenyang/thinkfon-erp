package com.yang.erp.common.advice;

import com.yang.erp.domain.vo.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 全局异常捕获处理
 * </p>
 *
 * @author 曹申阳
 * @since 2022-09-01 22:25:32
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionAdvice {

    @ExceptionHandler(value = Exception.class)
    public CommonResponse<String> handlerException(Exception e) {
        CommonResponse<String> response = new CommonResponse<String>().fail().setData(e.getMessage());
        log.error("itshare service has error [{}]", e.getMessage(), e);
        return response;
    }

    /**
     * 处理参数校验异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public CommonResponse<String> errorHandler(MethodArgumentNotValidException e) {
        e.printStackTrace();
        log.error("发生 {} 异常", "MethodArgumentNotValidException");
        List<ObjectError> allErrors = e.getBindingResult().getAllErrors();
        List<String> messages = new ArrayList<>();
        allErrors.forEach(error -> messages.add(error.getDefaultMessage()));
        return new CommonResponse<String>().fail().setMessage(StringUtils.join(messages,";"));
    }

    @ExceptionHandler(value = ErpException.class)
    public CommonResponse<String> handlerItShareException(ErpException e) {
        CommonResponse<String> response = new CommonResponse<String>(e.getCode()).setData(e.getMessage());
        log.error("itshare service has error [{}]", e.getMessage(), e);
        return response;
    }
}
