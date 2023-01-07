package com.yang.erp.common.advice;

import com.yang.erp.common.constant.ResponseCodeEnum;

/**
 * <p>
 * 自定义异常
 * </p>
 *
 * @author 曹申阳
 * @since 2022-10-12 15:13:04
 */
public class ItShareException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * 错误码
     */
    private final ResponseCodeEnum code;

    public ItShareException(ResponseCodeEnum code) {
        this.code = code;
    }

    public ResponseCodeEnum getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return code.getStateInfo();
    }
}
