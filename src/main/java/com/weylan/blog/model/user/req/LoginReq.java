package com.weylan.blog.model.user.req;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginReq {

    /**
     * 微信鉴权code
     */
    private String code;
    private String encryptedData;
    private String iv;
    /**
     * code 为空时采用用户名密码登录
     */
    private String userName;
    private String pwd;


}
