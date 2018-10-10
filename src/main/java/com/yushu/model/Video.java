package com.yushu.model;

import java.util.Date;
import javax.persistence.*;

public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 视频标题
     */
    private String title;

    /**
     * 概述
     */
    private String summary;

    /**
     * 封面图
     */
    @Column(name = "cover_img")
    private String coverImg;

    /**
     * 观看数
     */
    @Column(name = "view_num")
    private Integer viewNum;

    /**
     * 价格,分
     */
    private Integer price;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 0表示未上线，1表示上线
     */
    private Integer online;

    /**
     * 默认8.7，最高10分
     */
    private Double point;

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
     * 获取视频标题
     *
     * @return title - 视频标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置视频标题
     *
     * @param title 视频标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取概述
     *
     * @return summary - 概述
     */
    public String getSummary() {
        return summary;
    }

    /**
     * 设置概述
     *
     * @param summary 概述
     */
    public void setSummary(String summary) {
        this.summary = summary;
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
     * 获取观看数
     *
     * @return view_num - 观看数
     */
    public Integer getViewNum() {
        return viewNum;
    }

    /**
     * 设置观看数
     *
     * @param viewNum 观看数
     */
    public void setViewNum(Integer viewNum) {
        this.viewNum = viewNum;
    }

    /**
     * 获取价格,分
     *
     * @return price - 价格,分
     */
    public Integer getPrice() {
        return price;
    }

    /**
     * 设置价格,分
     *
     * @param price 价格,分
     */
    public void setPrice(Integer price) {
        this.price = price;
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
     * 获取0表示未上线，1表示上线
     *
     * @return online - 0表示未上线，1表示上线
     */
    public Integer getOnline() {
        return online;
    }

    /**
     * 设置0表示未上线，1表示上线
     *
     * @param online 0表示未上线，1表示上线
     */
    public void setOnline(Integer online) {
        this.online = online;
    }

    /**
     * 获取默认8.7，最高10分
     *
     * @return point - 默认8.7，最高10分
     */
    public Double getPoint() {
        return point;
    }

    /**
     * 设置默认8.7，最高10分
     *
     * @param point 默认8.7，最高10分
     */
    public void setPoint(Double point) {
        this.point = point;
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