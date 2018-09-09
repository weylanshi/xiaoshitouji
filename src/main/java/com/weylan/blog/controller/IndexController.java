package com.weylan.blog.controller;

import com.github.pagehelper.PageHelper;
import com.weylan.blog.mapper.EssayMapper;
import com.weylan.blog.model.entity.Essay;
import com.weylan.blog.model.res.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author shiweinan
 */
@RestController
@RequestMapping("/index")
public class IndexController {

    @Autowired
    private EssayMapper essayMapper;

    @RequestMapping("")
    public Result index() {
        PageHelper.startPage(1,1);
        List<Essay> essays = essayMapper.selectAll();
        return new Result<>().success(essays);
    }
}
