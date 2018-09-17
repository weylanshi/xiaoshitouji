package com.weylan.blog.model.user.res;

import com.weylan.blog.entity.Essay;
import com.weylan.blog.entity.User;

/**
 * @author shiweinan
 */
public class EssayDetailVo {

    private Integer essayId;
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

    public Integer getEssayId() {
        return essayId;
    }

    public void setEssayId(Integer essayId) {
        this.essayId = essayId;
    }

    public String getEssayTitle() {
        return essayTitle;
    }

    public void setEssayTitle(String essayTitle) {
        this.essayTitle = essayTitle;
    }

    public String getEssayDetail() {
        return essayDetail;
    }

    public void setEssayDetail(String essayDetail) {
        this.essayDetail = essayDetail;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    public Integer getEssayFavorite() {
        return essayFavorite;
    }

    public void setEssayFavorite(Integer essayFavorite) {
        this.essayFavorite = essayFavorite;
    }

    public Integer getEssayFootPrint() {
        return essayFootPrint;
    }

    public void setEssayFootPrint(Integer essayFootPrint) {
        this.essayFootPrint = essayFootPrint;
    }

    public Integer getEssayWords() {
        return essayWords;
    }

    public void setEssayWords(Integer essayWords) {
        this.essayWords = essayWords;
    }
}
