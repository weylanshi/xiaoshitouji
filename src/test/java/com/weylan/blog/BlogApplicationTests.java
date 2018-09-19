package com.weylan.blog;

import com.weylan.blog.mapper.EssayMapper;
import com.weylan.blog.mapper.UserMapper;
import com.weylan.blog.entity.Essay;
import com.weylan.blog.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BlogApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private EssayMapper essayMapper;

    @Test
    public void contextLoads() {
        User user = new User();
        user.setUserWxOpenId("11");
        user.setUserName("asd");
        user.setUserAvatar("asf");
        user.setUserPwd("saf");
        user.setUserStatus(1);
        userMapper.insert(user);
        System.out.println(user.getId());
    }

    @Test
    public void test1(){
        Essay essay = new Essay();
        essay.setEssayTitle("test");
        essay.setEssayDetail("--");
        essay.setEssayUserId(1+"");
        essay.setEssayCTime(new Date());
        essay.setEssayMTime(new Date());
        essay.setEssayStatus(1);
        int insert = essayMapper.insert(essay);
        System.out.println(insert);
        System.out.println(essay.getId());
    }

}
