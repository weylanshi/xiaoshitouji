package com.weylan.blog.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.weylan.blog.mapper.EssayMapper;
import com.weylan.blog.mapper.UserMapper;
import com.weylan.blog.entity.Essay;
import com.weylan.blog.entity.User;
import com.weylan.blog.model.essay.EssayVo;
import com.weylan.blog.model.user.res.EssayDetailVo;
import com.weylan.blog.model.Res;
import com.weylan.blog.service.EssayService;
import com.weylan.blog.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author shiweinan
 */
@RestController
@RequestMapping("/essay")
@Slf4j
public class EssayController {

    @Autowired
    private EssayMapper essayMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private EssayService essayService;

    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public Res listEssays(@RequestParam(required = false, defaultValue = "1") Integer pageNum,
                          @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        //todo 获取列表不需要太多详情,影响列表加载速度,待优化
        List<Essay> essays = essayMapper.selectAll();
        PageInfo<Essay> pageInfo = new PageInfo<>(essays);
        return new Res<>().success(pageInfo);
    }

    @GetMapping("/detail/{essayId}")
    public Res getEssayDetail(@PathVariable String essayId) {
        Essay essay = essayService.getEssayById(essayId);
        if (essay == null) {
            return new Res<>().error("no such essay ");
        }
        String userId = essay.getEssayUserId();
        //todo 加入用户缓存
        User user = userMapper.selectByPrimaryKey(userId);
        EssayDetailVo detailVo = new EssayDetailVo(essay, user);

        return new Res<>().success(detailVo);
    }


    @PostMapping("/insertEssay")
    public Res insertEssay(@RequestBody EssayVo essayVo) {
        try {
            essayService.insertOrUpdateEssay(essayVo);
            return new Res<>().success();
        } catch (Exception e) {
            return new Res<>().error(e.getMessage());
        }
    }
}
