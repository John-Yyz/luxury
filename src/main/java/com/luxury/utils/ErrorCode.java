package com.luxury.utils;

/**
 * 枚举错误码
 *
 * @author yuyz
 * @date 2021/11/29
 */
public enum ErrorCode {
    SUCCESS(200, "操作成功"),
    FAILED(201, "操作失败"),
    TOKEN_MISSING(300, "token丢失"),
    TOKEN_ERROR(301, "token认证失败"),
    PARAM_MISSING(400, "参数丢失"),
    PARAM_ERROR(401, "参数错误"),
    SYSTEM_ERROR(500, "系统错误"),
    UNKNOWN_ERROR(501, "未知错误"),
    NOT_ENTERPRISE(10002,"不是企业用户"),
    ENTERPRISE_INFO_ERROR(10003,"企业认证信息有误"),
    USER_NOT_EXIST(10004,"用户不存在"),
    THE_USER_IS_FREEZE(10005,"用户不存在"),
    TOKEN_INVALID(10006,"Token无效"),
    SIGN_ERROR(10007,"签名无效");

    public static final Integer MESSAGE_PARAM_MISSING = 400;

    /**
     * 错误码
     */
    private Integer code;
    /**
     * 错误描述
     */
    private String msg;

    public Integer getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }

    /**
     * 构造函数
     *
     * @param code
     * @param msg
     */
    private ErrorCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
