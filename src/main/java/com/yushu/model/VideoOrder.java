package com.yushu.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "video_order")
public class VideoOrder implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户标示
     */
    private String openid;

    /**
     * 订单唯一标识
     */
    @Column(name = "out_trade_no")
    private String outTradeNo;

    /**
     * 0表示未支付，1表示已支付
     */
    private Integer state;

    /**
     * 订单生成时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 支付回调时间
     */
    @Column(name = "notify_time")
    private Date notifyTime;

    /**
     * 支付金额，单位分
     */
    @Column(name = "total_fee")
    private Integer totalFee;

    /**
     * 微信昵称
     */
    private String nickname;

    /**
     * 微信头像
     */
    @Column(name = "head_img")
    private String headImg;

    /**
     * 视频主键
     */
    @Column(name = "video_id")
    private Integer videoId;

    /**
     * 视频名称
     */
    @Column(name = "video_title")
    private String videoTitle;

    /**
     * 视频图片
     */
    @Column(name = "video_img")
    private String videoImg;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 用户ip地址
     */
    private String ip;

    /**
     * 0表示未删除，1表示已经删除
     */
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
     * 获取用户标示
     *
     * @return openid - 用户标示
     */
    public String getOpenid() {
        return openid;
    }

    /**
     * 设置用户标示
     *
     * @param openid 用户标示
     */
    public void setOpenid(String openid) {
        this.openid = openid;
    }

    /**
     * 获取订单唯一标识
     *
     * @return out_trade_no - 订单唯一标识
     */
    public String getOutTradeNo() {
        return outTradeNo;
    }

    /**
     * 设置订单唯一标识
     *
     * @param outTradeNo 订单唯一标识
     */
    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    /**
     * 获取0表示未支付，1表示已支付
     *
     * @return state - 0表示未支付，1表示已支付
     */
    public Integer getState() {
        return state;
    }

    /**
     * 设置0表示未支付，1表示已支付
     *
     * @param state 0表示未支付，1表示已支付
     */
    public void setState(Integer state) {
        this.state = state;
    }

    /**
     * 获取订单生成时间
     *
     * @return create_time - 订单生成时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置订单生成时间
     *
     * @param createTime 订单生成时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取支付回调时间
     *
     * @return notify_time - 支付回调时间
     */
    public Date getNotifyTime() {
        return notifyTime;
    }

    /**
     * 设置支付回调时间
     *
     * @param notifyTime 支付回调时间
     */
    public void setNotifyTime(Date notifyTime) {
        this.notifyTime = notifyTime;
    }

    /**
     * 获取支付金额，单位分
     *
     * @return total_fee - 支付金额，单位分
     */
    public Integer getTotalFee() {
        return totalFee;
    }

    /**
     * 设置支付金额，单位分
     *
     * @param totalFee 支付金额，单位分
     */
    public void setTotalFee(Integer totalFee) {
        this.totalFee = totalFee;
    }

    /**
     * 获取微信昵称
     *
     * @return nickname - 微信昵称
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * 设置微信昵称
     *
     * @param nickname 微信昵称
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * 获取微信头像
     *
     * @return head_img - 微信头像
     */
    public String getHeadImg() {
        return headImg;
    }

    /**
     * 设置微信头像
     *
     * @param headImg 微信头像
     */
    public void setHeadImg(String headImg) {
        this.headImg = headImg;
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
     * 获取视频名称
     *
     * @return video_title - 视频名称
     */
    public String getVideoTitle() {
        return videoTitle;
    }

    /**
     * 设置视频名称
     *
     * @param videoTitle 视频名称
     */
    public void setVideoTitle(String videoTitle) {
        this.videoTitle = videoTitle;
    }

    /**
     * 获取视频图片
     *
     * @return video_img - 视频图片
     */
    public String getVideoImg() {
        return videoImg;
    }

    /**
     * 设置视频图片
     *
     * @param videoImg 视频图片
     */
    public void setVideoImg(String videoImg) {
        this.videoImg = videoImg;
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

    /**
     * 获取用户ip地址
     *
     * @return ip - 用户ip地址
     */
    public String getIp() {
        return ip;
    }

    /**
     * 设置用户ip地址
     *
     * @param ip 用户ip地址
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * 获取0表示未删除，1表示已经删除
     *
     * @return delete_status - 0表示未删除，1表示已经删除
     */
    public Boolean getDeleteStatus() {
        return deleteStatus;
    }

    /**
     * 设置0表示未删除，1表示已经删除
     *
     * @param deleteStatus 0表示未删除，1表示已经删除
     */
    public void setDeleteStatus(Boolean deleteStatus) {
        this.deleteStatus = deleteStatus;
    }
}