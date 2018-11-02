package com.yushu.model;

import java.util.Date;
import javax.persistence.*;

public class Chapter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 视频主键
     */
    @Column(name = "video_id")
    private Integer videoId;

    /**
     * 章节名称
     */
    private String title;

    /**
     * 章节顺序
     */
    private Integer ordered;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "delete_status")
    private Boolean deleteStatus;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取视频主键
     *
     * @return video_id - 视频主键
     */
    public Integer getVideoId() {
        return videoId;
    }

    /**
     * 设置视频主键
     *
     * @param videoId 视频主键
     */
    public void setVideoId(Integer videoId) {
        this.videoId = videoId;
    }

    /**
     * 获取章节名称
     *
     * @return title - 章节名称
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置章节名称
     *
     * @param title 章节名称
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取章节顺序
     *
     * @return ordered - 章节顺序
     */
    public Integer getOrdered() {
        return ordered;
    }

    /**
     * 设置章节顺序
     *
     * @param ordered 章节顺序
     */
    public void setOrdered(Integer ordered) {
        this.ordered = ordered;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return delete_status
     */
    public Boolean getDeleteStatus() {
        return deleteStatus;
    }

    /**
     * @param deleteStatus
     */
    public void setDeleteStatus(Boolean deleteStatus) {
        this.deleteStatus = deleteStatus;
    }
}