package com.weylan.blog.entity;

import javax.persistence.*;

@Table(name = "essay_detail")
public class EssayDetail {
    /**
     * essay Id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 文章详情
     */
    @Column(name = "essay_detail")
    private String essayDetail;

    /**
     * 获取essay Id
     *
     * @return id - essay Id
     */
    public String getId() {
        return id;
    }

    /**
     * 设置essay Id
     *
     * @param id essay Id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取文章详情
     *
     * @return essay_detail - 文章详情
     */
    public String getEssayDetail() {
        return essayDetail;
    }

    /**
     * 设置文章详情
     *
     * @param essayDetail 文章详情
     */
    public void setEssayDetail(String essayDetail) {
        this.essayDetail = essayDetail;
    }
}