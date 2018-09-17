package com.weylan.blog.service;

import com.weylan.blog.model.user.vo.WxUserVo;

import java.util.Map;

public interface UserService {
    Map<String, Object> login(WxUserVo wxUserVo);
}
