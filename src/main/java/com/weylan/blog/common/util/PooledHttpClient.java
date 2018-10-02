package com.weylan.blog.common.util;

import com.alibaba.fastjson.JSON;
import com.weylan.blog.config.http.HttpConnectionManager;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author shiweinan
 */
@Slf4j
@Component
public class PooledHttpClient {

    @Autowired
    HttpConnectionManager connManager;

    public <T> T get(String url, Class<T> clazz) {
        CloseableHttpClient httpClient = connManager.getHttpClient();
        HttpGet httpget = new HttpGet(url);
        String json = null;
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpget);
            HttpEntity entity = response.getEntity();
            json = EntityUtils.toString(entity);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return JSON.parseObject(json, clazz);
    }

    public <T> String get(String url) {
        CloseableHttpClient httpClient = connManager.getHttpClient();
        HttpGet httpget = new HttpGet(url);
        String json = null;
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpget);
            json = EntityUtils.toString(response.getEntity());
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return json;
    }


//    public

}
