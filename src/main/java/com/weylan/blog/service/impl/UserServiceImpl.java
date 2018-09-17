package com.weylan.blog.service.impl;

import com.weylan.blog.mapper.UserMapper;
import com.weylan.blog.entity.User;
import com.weylan.blog.model.user.vo.UserContext;
import com.weylan.blog.model.user.vo.WxUserVo;
import com.weylan.blog.service.UserService;
import com.weylan.blog.common.util.token.JwtTokenFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JwtTokenFactory tokenFactory;

    /**
     * 用户登录方,暂时只支持openId 登录
     * 有openId 登录,没有注册,再登录
     */
    public User userLogin(String openId) {
        User example = new User();
        example.setUserWxOpenId(openId);
        User user = userMapper.selectOne(example);
        if (user != null) {
            return user;
        } else {
            User newUser = new User();
            return null;
        }

    }


    @Override
    public Map<String, Object> login(WxUserVo userVo) {
        User example = new User();
        example.setUserWxOpenId(userVo.getOpenId());
        User user = userMapper.selectOne(example);
        if (user != null) {
            return createToken(user);
        } else {
            User newUser = new User();
            newUser.setUserName(userVo.getNickName());
            newUser.setUserAvatar(userVo.getAvatarUrl());
            newUser.setUserWxOpenId(userVo.getOpenId());
            newUser.setUserPwd("");
            newUser.setUserAlias(userVo.getNickName());
            newUser.setContry(userVo.getCountry());
            newUser.setProvince(userVo.getProvince());
            newUser.setCity(userVo.getCity());
            newUser.setGender(userVo.getGender());
            newUser.setUserStatus(1);
            newUser.setUserEssayCount(0);
            newUser.setUserPhotoCount(0);
            int insert = userMapper.insert(newUser);
            if (insert > 0) {
                return createToken(newUser);
            }
            return null;
        }
    }

    private Map<String, Object> createToken(User user) {
        UserContext userContext = new UserContext(user);
        HashMap<String, Object> result = new HashMap<>(2);
        result.put("token", tokenFactory.createAccessToken(userContext));
        result.put("refreshToken", tokenFactory.createRefreshToken(userContext));
        return result;
    }

}
