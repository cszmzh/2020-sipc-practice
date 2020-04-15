package com.code515.report.report_demo.Enums;

import lombok.Getter;

@Getter
public enum ResultEnum {

    PARAM_ERROR(1,"参数不正确"),
    LOGIN_ERROR(2,"未登录状态"),
    USER_NULL(3,"用户不存在")
    ;

    private Integer code;
    private String message;

    ResultEnum(Integer code, String message){
        this.code = code;
        this.message = message;
    }

}
