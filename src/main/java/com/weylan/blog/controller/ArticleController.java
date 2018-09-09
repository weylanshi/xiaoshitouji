package com.weylan.blog.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author shiweinan
 */
@RestController
@RequestMapping("/article")
public class ArticleController {

    @RequestMapping("")
    public String hello(){
        return "hello";
    }


}
