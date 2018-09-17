package com.weylan.blog.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.weylan.blog.mapper.EssayMapper;
import com.weylan.blog.mapper.UserMapper;
import com.weylan.blog.entity.Essay;
import com.weylan.blog.entity.User;
import com.weylan.blog.model.user.res.EssayDetailVo;
import com.weylan.blog.model.Result;
import com.weylan.blog.service.EssayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author shiweinan
 */
@RestController
@RequestMapping("/essay")
public class EssayController {

    @Autowired
    private EssayMapper essayMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private EssayService essayService;

    @GetMapping("/list")
    public Result listEssays(@RequestParam(required = false, defaultValue = "1") Integer pageNum,
                             @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        //todo 获取列表不需要太多详情,影响列表加载速度,待优化
        List<Essay> essays = essayMapper.selectAll();
        PageInfo<Essay> pageInfo = new PageInfo<>(essays);
        return new Result<>().success(pageInfo);
    }

    @GetMapping("/detail/{essayId}")
    public Result getEssayDetail(@PathVariable Integer essayId) {
//        Essay essay = essayMapper.selectByPrimaryKey(essayId);
        Essay essay = essayService.getEssayById(essayId);
        String userId = essay.getEssayUserId();
        //todo 加入用户缓存
        User user = userMapper.selectByPrimaryKey(userId);
        EssayDetailVo detailVo = new EssayDetailVo(essay, user);

        return new Result<>().success(detailVo);
    }
}
