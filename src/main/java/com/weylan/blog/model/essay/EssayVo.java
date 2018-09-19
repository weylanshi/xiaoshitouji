package com.weylan.blog.model.essay;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author shiweinan
 */
@Getter
@Setter
@ToString
public class EssayVo implements Serializable {

    private String id;
    private String userId;
    private String title;
    private String detail;


}
