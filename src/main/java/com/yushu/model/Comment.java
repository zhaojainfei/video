package com.yushu.model;

import java.util.Date;
import javax.persistence.*;

public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 内容
     */
    private String content;

    @Column(name = "user_id")
    private Integer userId;

    /**
     * 用户头像
     */
    @Column(name = "head_img")
    private String headImg;

    /**
     * 昵称
     */
    private String name;

    /**
     * 评分，10分满分
     */
    private Double point;

    /**
     * 点赞数
     */
    private Integer up;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 订单id
     */
    @Column(name = "order_id")
    private Integer orderId;

    /**
     * 视频id
     */
    @Column(name = "video_id")
    private Integer videoId;

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
     * 获取内容
     *
     * @return content - 内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置内容
     *
     * @param content 内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return user_id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取用户头像
     *
     * @return head_img - 用户头像
     */
    public String getHeadImg() {
        return headImg;
    }

    /**
     * 设置用户头像
     *
     * @param headImg 用户头像
     */
    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    /**
     * 获取昵称
     *
     * @return name - 昵称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置昵称
     *
     * @param name 昵称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取评分，10分满分
     *
     * @return point - 评分，10分满分
     */
    public Double getPoint() {
        return point;
    }

    /**
     * 设置评分，10分满分
     *
     * @param point 评分，10分满分
     */
    public void setPoint(Double point) {
        this.point = point;
    }

    /**
     * 获取点赞数
     *
     * @return up - 点赞数
     */
    public Integer getUp() {
        return up;
    }

    /**
     * 设置点赞数
     *
     * @param up 点赞数
     */
    public void setUp(Integer up) {
        this.up = up;
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
     * 获取订单id
     *
     * @return order_id - 订单id
     */
    public Integer getOrderId() {
        return orderId;
    }

    /**
     * 设置订单id
     *
     * @param orderId 订单id
     */
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
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