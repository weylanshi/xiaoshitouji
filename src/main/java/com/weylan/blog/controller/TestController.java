package com.weylan.blog.controller;

import com.weylan.blog.annotation.AuthorizationAnnotation;
import com.weylan.blog.annotation.CurrentUser;
import com.weylan.blog.common.util.token.JwtTokenFactory;
import com.weylan.blog.common.util.token.model.AccessToken;
import com.weylan.blog.model.user.vo.UserContent;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private JwtTokenFactory<UserContent> tokenFactory;

    @GetMapping("/test1")
    public String test1(){
        return "ok";
    }

    @AuthorizationAnnotation
    @GetMapping("/test2")
    public Object test2(@CurrentUser UserContent userContent){
        return userContent;
    }

    @GetMapping("/createToken")
    public Object createToken(){
        UserContent user = new UserContent();
        user.setUserId("1111");
        user.setUserName("weylan");
        AccessToken accessToken = tokenFactory.createAccessToken(user);
        System.out.println(accessToken);
        return tokenFactory.createAccessToken(user);
    }


}
