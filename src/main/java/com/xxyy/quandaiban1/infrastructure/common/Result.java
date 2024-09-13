package com.xxyy.quandaiban1.infrastructure.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author xy
 * @date 2023-09-12 21:16
 */
@Getter
public class Result<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 响应码
     *
     * @mock 200
     */
    private Integer code;
    /**
     * 响应信息
     *
     * @mock 操作成功
     */
    private String msg;
    /**
     * 响应数据
     */
    private T data;
    /**
     * 扩展内容
     */
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private Object exts;

    protected Result() {
    }

    public static <T> Result<T> of(Integer code, String msg, T data) {
        Result<T> res = new Result<>();
        res.code = code;
        res.msg = msg;
        res.data = data;
        return res;
    }

    //region R.data

    public static <T> Result<T> data(T data) {
        return Result.data(ResultTags.SUCCEEDED, data);
    }

    public static <T> Result<T> data(ResultTags tag, T data) {
        return Result.of(tag.getCode(), tag.getMsg(), data);
    }

    //endregion

    //region R.ok

    public static Result<?> ok() {
        return Result.ok(ResultTags.SUCCEEDED);
    }

    public static Result<?> ok(ResultTag tag) {
        return Result.of(tag.getCode(), tag.getMsg(), null);
    }

    public static Result<?> ok(String msg) {
        return Result.of(ResultTags.SUCCEEDED.getCode(), msg, null);
    }

    //endregion

    //region R.fail

    public static Result<?> fail() {
        return Result.fail(ResultTags.FAILED);
    }

    public static Result<?> fail(ResultTag tag) {
        return Result.of(tag.getCode(), tag.getMsg(), null);
    }

    public static Result<?> fail(String msg) {
        return Result.of(ResultTags.FAILED.getCode(), msg, null);
    }

    //endregion

    public Result<T> with(Object exts) {
        this.exts = exts;
        return this;
    }

    public boolean succeed() {
        return Objects.equals(this.code, ResultTags.SUCCEEDED.getCode());
    }

    //region ...

    @Override
    public String toString() {
        return "R{code=" + code + ", msg='" + msg + "', data=" + data + ", exts=" + exts + '}';
    }

    //endregion
}