package com.yang.erp.common.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>
 * 统一状态码返回枚举
 * </p>
 *
 * @author 曹申阳
 * @since 2022-10-11 09:56:25
 */
@Getter
@AllArgsConstructor
public enum ResponseCodeEnum {


    /**
     * 服务响应状态
     */
    SUCCESS(20000, "请求成功"),
    FAIL(50000, "服务异常"),


    /**
     *  登录业务响应状态码
     */
    NOT_AUTHORIZED(40001, "未认证,请登录"),
    NOT_ACCESS(40003, "用户未授权，拒绝访问"),
    LOGIN_ERROR(41001,"用户名或者密码错误"),
    USER_ALREADY_EXIST(41002,"用户已存在"),
    USER_ADDRESS_NOT_EXIST(41003,"地址不存在"),
    USER_PARSE_ERROR(41004,"用户信息校验异常，请重写登录"),
    REFRESH_TOKEN_ERROR(41005, "请求不合法"),
    USERNAME_NOT_EXIST(41006, "用户名不存在"),
    USERNAME_EXIST(41007, "用户名重复"),
    PHONE_NUM_EXIST(41008, "手机号已被绑定，请更换手机号"),
    EMAIL_EXIST(41009, "邮箱已被绑定，请更换邮箱"),
    WECHAT_TICKET_ERROR(42001, "获取二维码异常，请稍后再试！"),
    WECHAT_MENU_ERROR(42002, "获取菜单异常，请稍后再试！"),
    WECHAT_MENU_ERROR_CREAT(42003, "创建菜单异常，请稍后再试！"),
    // 部门相关异常
    DEPT_EXIST(51001, "同一层级下存在相同名称的部门！"),
    DEPT_NOT_EXIST(51002, "操作部门不存在！"),
    ;

    /**
     * 执行状态码
     */
    private final int state;
    /**
     * 执行状态描述
     */
    private final String stateInfo;
}
