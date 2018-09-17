package com.weylan.blog.entity;

import javax.persistence.*;

@Table(name = "essay_favorite")
public class EssayFavorite {
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 文章id
     */
    @Column(name = "essay_id")
    private Integer essayId;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private Integer userId;

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
     * 获取文章id
     *
     * @return essay_id - 文章id
     */
    public Integer getEssayId() {
        return essayId;
    }

    /**
     * 设置文章id
     *
     * @param essayId 文章id
     */
    public void setEssayId(Integer essayId) {
        this.essayId = essayId;
    }

    /**
     * 获取用户id
     *
     * @return user_id - 用户id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置用户id
     *
     * @param userId 用户id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}