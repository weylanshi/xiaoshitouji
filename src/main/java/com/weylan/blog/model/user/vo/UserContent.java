package com.weylan.blog.model.user.vo;

import com.weylan.blog.entity.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserContent {
    private String userId;
    private String userName;

    public UserContent() {
    }

    public UserContent(User user) {
        this.userId = user.getId();
        this.userName = user.getUserName();
    }
}
