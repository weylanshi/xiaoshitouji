package com.weylan.blog.model.res;

import java.util.HashMap;

/**
 * @author shiweinan
 */
public class Result<T> {

    private T data;
    private Boolean success;
    private String msg;

    public Result success() {
        Result<Object> result = new Result<>();
        result.data = "";
        result.msg = "";
        result.success = true;
        return result;
    }

    public Result<T> success(T t) {
        Result<T> result = new Result<>();
        result.data = t;
        result.msg = "";
        result.success = true;
        return result;
    }

    public Result error(String msg) {
        Result<Object> result = new Result<>();
        result.success = false;
        result.msg = msg;
        result.data = "";
        return result;
    }


    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
