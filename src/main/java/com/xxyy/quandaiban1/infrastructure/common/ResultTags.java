package com.xxyy.quandaiban1.infrastructure.common;

/**
 * @author xy
 * @date 2023-09-13 12:36
 */
public enum ResultTags  implements ResultTag{
    SUCCEEDED(200, "操作成功"),

    FAILED(500, "操作失败"),
    ;

    private final Integer code;
    private final String msg;

    ResultTags(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }
}
