package com.yushu.model;

import java.util.Date;
import javax.persistence.*;

public class Episode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 集标题
     */
    private String title;

    /**
     * 第几集
     */
    private Integer num;

    /**
     * 时长 分钟，单位
     */
    private String duration;

    /**
     * 封面图
     */
    @Column(name = "cover_img")
    private String coverImg;

    /**
     * 视频id
     */
    @Column(name = "video_id")
    private Integer videoId;

    /**
     * 集概述
     */
    private String summary;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 章节主键id
     */
    @Column(name = "chapter_id")
    private Integer chapterId;

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
     * 获取集标题
     *
     * @return title - 集标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置集标题
     *
     * @param title 集标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取第几集
     *
     * @return num - 第几集
     */
    public Integer getNum() {
        return num;
    }

    /**
     * 设置第几集
     *
     * @param num 第几集
     */
    public void setNum(Integer num) {
        this.num = num;
    }

    /**
     * 获取时长 分钟，单位
     *
     * @return duration - 时长 分钟，单位
     */
    public String getDuration() {
        return duration;
    }

    /**
     * 设置时长 分钟，单位
     *
     * @param duration 时长 分钟，单位
     */
    public void setDuration(String duration) {
        this.duration = duration;
    }

    /**
     * 获取封面图
     *
     * @return cover_img - 封面图
     */
    public String getCoverImg() {
        return coverImg;
    }

    /**
     * 设置封面图
     *
     * @param coverImg 封面图
     */
    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg;
    }

    /**
     * 获取视频id
     *
     * @return video_id - 视频id
     */
    public Integer getVideoId() {
        return videoId;
    }

    /**
     * 设置视频id
     *
     * @param videoId 视频id
     */
    public void setVideoId(Integer videoId) {
        this.videoId = videoId;
    }

    /**
     * 获取集概述
     *
     * @return summary - 集概述
     */
    public String getSummary() {
        return summary;
    }

    /**
     * 设置集概述
     *
     * @param summary 集概述
     */
    public void setSummary(String summary) {
        this.summary = summary;
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
     * 获取章节主键id
     *
     * @return chapter_id - 章节主键id
     */
    public Integer getChapterId() {
        return chapterId;
    }

    /**
     * 设置章节主键id
     *
     * @param chapterId 章节主键id
     */
    public void setChapterId(Integer chapterId) {
        this.chapterId = chapterId;
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