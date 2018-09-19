package com.weylan.blog.service;

import com.weylan.blog.entity.Essay;
import com.weylan.blog.model.essay.EssayVo;

/**
 * @author shiweinan
 */
public interface EssayService {

    /**
     * 新增或修改文章 根据id判断新增还是修改
     */
    void insertOrUpdateEssay(EssayVo essayVo);

    /**
     * 获取文章详情
     * @param essayId
     * @return
     */
    Essay getEssayById(String essayId);
}
