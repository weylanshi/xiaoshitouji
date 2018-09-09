package com.weylan.blog.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.weylan.blog.mapper.EssayMapper;
import com.weylan.blog.model.entity.Essay;
import com.weylan.blog.model.res.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author shiweinan
 */
@RestController
@RequestMapping("/article")
public class EssayController {

    @Autowired
    private EssayMapper essayMapper;

    @GetMapping("/list")
    public Result listArticles(@RequestParam(required = false,defaultValue = "1") Integer pageNum, @RequestParam(required = false,defaultValue = "10") Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Essay> essays = essayMapper.selectAll();
        PageInfo<Essay> pageInfo = new PageInfo<>(essays);
        return new Result<>().success(pageInfo);
    }

}
