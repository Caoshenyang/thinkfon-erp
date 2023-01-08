package com.yang.erp.vo;

import com.yang.erp.common.constant.ResponseCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 统一返回结果
 * </p>
 *
 * @author 曹申阳
 * @since 2022-09-01 17:42:02
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain=true)
public class CommonResponse<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 错误码
     */
    private Integer code;

    /**
     * 错误消息
     */
    private String message;

    /**
     * 泛型响应数据
     */
    private T data;

    public CommonResponse(ResponseCodeEnum responseCodeEnum) {
        this.code = responseCodeEnum.getState();
        this.message = responseCodeEnum.getStateInfo();
    }

    public CommonResponse<T> success() {
        return new CommonResponse<>(ResponseCodeEnum.SUCCESS);
    }

    public CommonResponse<T> success(T data) {
        return new CommonResponse<T>().success().setData(data);
    }

    public CommonResponse<T> fail() {
        return new CommonResponse<>(ResponseCodeEnum.FAIL);
    }
}
