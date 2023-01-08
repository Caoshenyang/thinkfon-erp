package com.yang.erp.common.advice;

import com.yang.erp.common.annotation.IgnoreResponseAdvice;
import com.yang.erp.common.constant.ResponseCodeEnum;
import com.yang.erp.vo.CommonResponse;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * <p>
 * 实现统一响应
 * </p>
 *
 * @author 曹申阳
 * @since 2022-09-01 17:59:11
 */
@RestControllerAdvice(value = "com.yang.itshare")
public class CommonResponseDataAdvice implements ResponseBodyAdvice<Object> {

    /**
     * 判断是否需要对响应进行处理
     *
     * @return false 不处理 true 在 beforeBodyWrite() 中进行处理
     */
    @Override
    @SuppressWarnings("all")
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {

        // 如果 Controller 被 IgnoreResponseAdvice 修饰直接返回 false
        if (methodParameter.getDeclaringClass().isAnnotationPresent(IgnoreResponseAdvice.class)) {
            return false;
        }
        // 如果 method 被 IgnoreResponseAdvice 修饰直接返回 false
        if (methodParameter.getMethod().isAnnotationPresent(IgnoreResponseAdvice.class)) {
            return false;
        }

        return true;
    }

    /**
     * 对返回的结果进行包装处理
     *
     * @param o 返回的结果对象
     * @return 包装完成的 统一返回结果对象 CommonResponse
     */
    @Override
    @SuppressWarnings("all")
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {

        // 定义最终的返回对象
        CommonResponse<Object> response = new CommonResponse<>(ResponseCodeEnum.SUCCESS);
        if (null == o) {
            return response;
        } else if (o instanceof CommonResponse) {
            response = (CommonResponse<Object>) o;
        } else {
            response.setData(o);
        }
        return response;
    }
}
