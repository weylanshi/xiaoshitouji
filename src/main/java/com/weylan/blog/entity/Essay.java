package com.weylan.blog.entity;

import java.util.Date;
import javax.persistence.*;

public class Essay {
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "select replace(uuid(),'-','')")
    private String id;

    /**
     * 标题
     */
    @Column(name = "essay_title")
    private String essayTitle;

    /**
     * 点赞统计
     */
    @Column(name = "essay_favorite_count")
    private Integer essayFavoriteCount;

    /**
     * 足迹统计
     */
    @Column(name = "essay_footprint_count")
    private Integer essayFootprintCount;

    /**
     * 字数统计
     */
    @Column(name = "essay_words_count")
    private Integer essayWordsCount;

    /**
     * 所属用户id
     */
    @Column(name = "essay_user_id")
    private String essayUserId;

    /**
     * 创建时间
     */
    @Column(name = "essay_c_time")
    private Date essayCTime;

    /**
     * 修改时间
     */
    @Column(name = "essay_m_time")
    private Date essayMTime;

    /**
     * 状态 0,删除,1,正常
     */
    @Column(name = "essay_status")
    private Integer essayStatus;

    /**
     * 详情
     */
    @Column(name = "essay_detial")
    private String essayDetial;

    /**
     * 获取id
     *
     * @return id - id
     */
    public String getId() {
        return id;
    }

    /**
     * 设置id
     *
     * @param id id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取标题
     *
     * @return essay_title - 标题
     */
    public String getEssayTitle() {
        return essayTitle;
    }

    /**
     * 设置标题
     *
     * @param essayTitle 标题
     */
    public void setEssayTitle(String essayTitle) {
        this.essayTitle = essayTitle;
    }

    /**
     * 获取点赞统计
     *
     * @return essay_favorite_count - 点赞统计
     */
    public Integer getEssayFavoriteCount() {
        return essayFavoriteCount;
    }

    /**
     * 设置点赞统计
     *
     * @param essayFavoriteCount 点赞统计
     */
    public void setEssayFavoriteCount(Integer essayFavoriteCount) {
        this.essayFavoriteCount = essayFavoriteCount;
    }

    /**
     * 获取足迹统计
     *
     * @return essay_footprint_count - 足迹统计
     */
    public Integer getEssayFootprintCount() {
        return essayFootprintCount;
    }

    /**
     * 设置足迹统计
     *
     * @param essayFootprintCount 足迹统计
     */
    public void setEssayFootprintCount(Integer essayFootprintCount) {
        this.essayFootprintCount = essayFootprintCount;
    }

    /**
     * 获取字数统计
     *
     * @return essay_words_count - 字数统计
     */
    public Integer getEssayWordsCount() {
        return essayWordsCount;
    }

    /**
     * 设置字数统计
     *
     * @param essayWordsCount 字数统计
     */
    public void setEssayWordsCount(Integer essayWordsCount) {
        this.essayWordsCount = essayWordsCount;
    }

    /**
     * 获取所属用户id
     *
     * @return essay_user_id - 所属用户id
     */
    public String getEssayUserId() {
        return essayUserId;
    }

    /**
     * 设置所属用户id
     *
     * @param essayUserId 所属用户id
     */
    public void setEssayUserId(String essayUserId) {
        this.essayUserId = essayUserId;
    }

    /**
     * 获取创建时间
     *
     * @return essay_c_time - 创建时间
     */
    public Date getEssayCTime() {
        return essayCTime;
    }

    /**
     * 设置创建时间
     *
     * @param essayCTime 创建时间
     */
    public void setEssayCTime(Date essayCTime) {
        this.essayCTime = essayCTime;
    }

    /**
     * 获取修改时间
     *
     * @return essay_m_time - 修改时间
     */
    public Date getEssayMTime() {
        return essayMTime;
    }

    /**
     * 设置修改时间
     *
     * @param essayMTime 修改时间
     */
    public void setEssayMTime(Date essayMTime) {
        this.essayMTime = essayMTime;
    }

    /**
     * 获取状态 0,删除,1,正常
     *
     * @return essay_status - 状态 0,删除,1,正常
     */
    public Integer getEssayStatus() {
        return essayStatus;
    }

    /**
     * 设置状态 0,删除,1,正常
     *
     * @param essayStatus 状态 0,删除,1,正常
     */
    public void setEssayStatus(Integer essayStatus) {
        this.essayStatus = essayStatus;
    }

    /**
     * 获取详情
     *
     * @return essay_detial - 详情
     */
    public String getEssayDetial() {
        return essayDetial;
    }

    /**
     * 设置详情
     *
     * @param essayDetial 详情
     */
    public void setEssayDetial(String essayDetial) {
        this.essayDetial = essayDetial;
    }
}