package com.weylan.blog.model.entity;

import javax.persistence.*;

public class User {
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 姓名
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * 头像
     */
    @Column(name = "user_avatar")
    private String userAvatar;

    /**
     * 密码
     */
    @Column(name = "user_pwd")
    private String userPwd;

    /**
     * 别名
     */
    @Column(name = "user_alias")
    private String userAlias;

    /**
     * 微信
     */
    @Column(name = "user_wx")
    private String userWx;

    /**
     * 文章统计
     */
    @Column(name = "user_essay_count")
    private Integer userEssayCount;

    /**
     * 图片统计
     */
    @Column(name = "user_photo_count")
    private Integer userPhotoCount;

    /**
     * 状态
     */
    @Column(name = "user_status")
    private Integer userStatus;

    /**
     * 获取id
     *
     * @return id - id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置id
     *
     * @param id id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取姓名
     *
     * @return user_name - 姓名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置姓名
     *
     * @param userName 姓名
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 获取头像
     *
     * @return user_avatar - 头像
     */
    public String getUserAvatar() {
        return userAvatar;
    }

    /**
     * 设置头像
     *
     * @param userAvatar 头像
     */
    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    /**
     * 获取密码
     *
     * @return user_pwd - 密码
     */
    public String getUserPwd() {
        return userPwd;
    }

    /**
     * 设置密码
     *
     * @param userPwd 密码
     */
    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    /**
     * 获取别名
     *
     * @return user_alias - 别名
     */
    public String getUserAlias() {
        return userAlias;
    }

    /**
     * 设置别名
     *
     * @param userAlias 别名
     */
    public void setUserAlias(String userAlias) {
        this.userAlias = userAlias;
    }

    /**
     * 获取微信
     *
     * @return user_wx - 微信
     */
    public String getUserWx() {
        return userWx;
    }

    /**
     * 设置微信
     *
     * @param userWx 微信
     */
    public void setUserWx(String userWx) {
        this.userWx = userWx;
    }

    /**
     * 获取文章统计
     *
     * @return user_essay_count - 文章统计
     */
    public Integer getUserEssayCount() {
        return userEssayCount;
    }

    /**
     * 设置文章统计
     *
     * @param userEssayCount 文章统计
     */
    public void setUserEssayCount(Integer userEssayCount) {
        this.userEssayCount = userEssayCount;
    }

    /**
     * 获取图片统计
     *
     * @return user_photo_count - 图片统计
     */
    public Integer getUserPhotoCount() {
        return userPhotoCount;
    }

    /**
     * 设置图片统计
     *
     * @param userPhotoCount 图片统计
     */
    public void setUserPhotoCount(Integer userPhotoCount) {
        this.userPhotoCount = userPhotoCount;
    }

    /**
     * 获取状态
     *
     * @return user_status - 状态
     */
    public Integer getUserStatus() {
        return userStatus;
    }

    /**
     * 设置状态
     *
     * @param userStatus 状态
     */
    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }
}