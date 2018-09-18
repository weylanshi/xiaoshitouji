package com.weylan.blog.model.user.res;

import com.weylan.blog.entity.Essay;
import com.weylan.blog.entity.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author shiweinan
 */
@Getter
@Setter
@ToString
public class EssayDetailVo {

    private String essayId;
    private String essayTitle;
    private String essayDetail;
    private String userId;
    private String userName;
    private String userAvatar;
    private Integer essayFavorite;
    private Integer essayFootPrint;
    private Integer essayWords;

    public EssayDetailVo(Essay essay, User user) {
        this.essayId = essay.getId();
        this.essayTitle = essay.getEssayTitle();
        this.essayDetail = essay.getEssayDetial();
        this.userId = user.getId();
        this.userName = user.getUserName();
        this.userAvatar = user.getUserAvatar();
        this.essayFavorite = essay.getEssayFavoriteCount();
        this.essayFootPrint = essay.getEssayFootprintCount();
        this.essayWords = essay.getEssayWordsCount();

    }

    public EssayDetailVo() {
    }


}
