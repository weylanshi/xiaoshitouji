package com.weylan.blog.model.user.vo;

import com.weylan.blog.entity.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserContext {
    private String userId;
    private String userName;
    private String userAvatar;

    public UserContext() {
    }

    public UserContext(User user) {
        this.userId = user.getId();
        this.userName = user.getUserName();
        this.userAvatar = user.getUserAvatar();

    }
}
