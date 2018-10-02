package com.weylan.blog.model;

import com.weylan.blog.common.enums.ResEnum;
import lombok.Getter;
import lombok.Setter;

/**
 * @author shiweinan
 */
@Getter
@Setter
public class Res<T> {

    private T data;
    private int status;
    private String msg;

    public Res success() {
        Res<Object> res = new Res<>();
        res.data = "";
        res.msg = "";
        res.status = ResEnum.SUCCESS.getCode();
        return res;
    }

    public Res<T> success(T t) {
        Res<T> res = new Res<>();
        res.data = t;
        res.msg = "";
        res.status = ResEnum.SUCCESS.getCode();
        return res;
    }

    public Res error() {
        Res<Object> res = new Res<>();
        res.status = ResEnum.ERROR.getCode();
        res.msg = ResEnum.ERROR.getMsg();
        res.data = "";
        return res;
    }


    public Res error(String msg) {
        Res<Object> res = new Res<>();
        res.status = ResEnum.ERROR.getCode();
        res.msg = msg;
        res.data = "";
        return res;
    }

    public Res error(ResEnum resEnum) {
        Res<Object> res = new Res<>();
        res.status = resEnum.getCode();
        res.msg = resEnum.getMsg();
        res.data = "";
        return res;
    }


}
