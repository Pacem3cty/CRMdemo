package com.example.crmdemo.config;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel()
public class ApiResult<T> {

    private static final Object RESULT_OBJECT=new Object();

    @ApiModelProperty(value = "编码", name = "code", dataType = "int", example = "200")
    private Integer code;

    @ApiModelProperty(value = "提示", name = "msg", dataType = "string", example = "success")
    private String msg;

    @ApiModelProperty(value = "内容", name = "data", dataType = "t")
    private T data;

    public ApiResult() {
    }

    public ApiResult(ApiHttpCode code, T data) {
        this.code = code.getCode();
        this.msg = code.getDesc();
        this.data = data;
    }

    public ApiResult(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static ApiResult<Object> ok() {
        return ApiResult.ok(ApiHttpCode.SUCCESS, RESULT_OBJECT);
    }

    public static <T> ApiResult<T> ok(T data) {
        return ApiResult.ok(ApiHttpCode.SUCCESS, data);
    }

    public static <T> ApiResult<T> ok(ApiHttpCode code, T data) {
        return ApiResult.ok(code.getCode(), code.getDesc(), data);
    }

    public static <T> ApiResult<T> ok(Integer code, String msg, T data) {
        return new ApiResult(code, msg, data);
    }

    public static ApiResult<Object> error() {
        return ApiResult.error(ApiHttpCode.ERROR, new Object());
    }

    public static <T> ApiResult<T> error(T data) {
        return ApiResult.error(ApiHttpCode.ERROR, data);
    }

    public static <T> ApiResult<T> error(ApiHttpCode code) {
        return ApiResult.error(code.getCode(),code.getDesc(),null);
    }

    public static <T> ApiResult<T> error(ApiHttpCode code, T data) {
        return ApiResult.error(code.getCode(), code.getDesc(), data);
    }

    public static <T> ApiResult<T> error(Integer code, String msg) {
        return ApiResult.error(code, msg, null);
    }

    public static <T> ApiResult<T> error(Integer code, String msg, T data) {
        return new ApiResult(code, msg, data);
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }
}
