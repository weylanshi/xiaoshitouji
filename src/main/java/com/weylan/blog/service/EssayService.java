package com.weylan.blog.service;

import com.weylan.blog.entity.Essay;

/**
 * @author shiweinan
 */
public interface EssayService {

    /**
     *
     * @param essayId
     * @return
     */
    Essay getEssayById(String  essayId);
}
