package xyz.zzyitj.ils.enums;

import lombok.Getter;

/**
 * @author intent
 */

@Getter
public enum ResultEnum {
    SUCCESS(200, "成功"),
    ERROR(500, "错误"),
    FAIL(400, "失败"),
    //    账号用户相关
    UNKNOWN_ACCOUNT(1000, "未知账号"),
    LOCK_ACCOUNT(1001, "账号被锁定"),
    AUTHENTICATION_ERROR(1002, "账号或密码错误"),
    // 权限相关
    UNAUTHORIZED_ERROR(1100, "未授权，无法访问"),
    ;
    private int code;
    private String message;

    ResultEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String toString() {
        return "{\"code\":" + code + ",\"message\":\"" + message + "\"}";
    }
}
