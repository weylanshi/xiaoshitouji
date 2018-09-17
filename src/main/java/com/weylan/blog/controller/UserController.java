package com.weylan.blog.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.weylan.blog.config.GlobalValueSetting;
import com.weylan.blog.model.user.req.LoginReq;
import com.weylan.blog.model.Result;
import com.weylan.blog.service.UserService;
import com.weylan.blog.common.util.AesUtil;
import com.weylan.blog.common.util.PooledHttpClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import com.weylan.blog.model.user.vo.WxUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

/**
 * @author shiweinan
 */
@RestController
@RequestMapping("/login")
@Slf4j
public class UserController {

    @Autowired
    private GlobalValueSetting globalValueSetting;

    @Autowired
    private PooledHttpClient pooledHttpClient;

    @Autowired
    private UserService userService;

    @PostMapping("/onLogin")
    public Result userLogin(@RequestBody LoginReq loginReq) throws IOException {
        String code = loginReq.getCode();

        if (StringUtils.isNotBlank(code)) {
            String appId = globalValueSetting.getMiniProgramAppId();
            String appSecret = globalValueSetting.getMiniProgramAppSecret();
            String url = "https://api.weixin.qq.com/sns/jscode2session"
                    + "?appid=" + appId
                    + "&secret=" + appSecret
                    + "&js_code=" + code
                    + "&grant_type=authorization_code";
            String resStr = pooledHttpClient.get(url);
            JSONObject res = JSON.parseObject(resStr);
            String sessionKey = res.getString("session_key");
            String encryptedData = loginReq.getEncryptedData();
            String iv = loginReq.getIv();
            try {
                String result = AesUtil.decrypt(encryptedData, sessionKey, iv, "UTF-8");
                WxUserVo wxUserVo = JSON.parseObject(result, WxUserVo.class);
                Map<String, Object> loginRes = userService.login(wxUserVo);
                if (loginRes == null) {
                    return new Result().error("登录失败");
                }
                return new Result<>().success(loginRes);
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        }
        return new Result().error("code 为空");
    }

    public Result getUserDetail() {
        return null;
    }


}
