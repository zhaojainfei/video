package com.yushu.model;

import java.util.Date;
import javax.persistence.*;

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 微信openid
     */
    private String openid;

    /**
     * 昵称
     */
    private String name;

    /**
     * 头像
     */
    @Column(name = "head_img")
    private String headImg;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 用户签名
     */
    private String sign;

    /**
     * 0表示女，1表示男
     */
    private Byte sex;

    /**
     * 城市
     */
    private String city;

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
     * 获取微信openid
     *
     * @return openid - 微信openid
     */
    public String getOpenid() {
        return openid;
    }

    /**
     * 设置微信openid
     *
     * @param openid 微信openid
     */
    public void setOpenid(String openid) {
        this.openid = openid;
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
     * 获取头像
     *
     * @return head_img - 头像
     */
    public String getHeadImg() {
        return headImg;
    }

    /**
     * 设置头像
     *
     * @param headImg 头像
     */
    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    /**
     * 获取手机号
     *
     * @return phone - 手机号
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置手机号
     *
     * @param phone 手机号
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取用户签名
     *
     * @return sign - 用户签名
     */
    public String getSign() {
        return sign;
    }

    /**
     * 设置用户签名
     *
     * @param sign 用户签名
     */
    public void setSign(String sign) {
        this.sign = sign;
    }

    /**
     * 获取0表示女，1表示男
     *
     * @return sex - 0表示女，1表示男
     */
    public Byte getSex() {
        return sex;
    }

    /**
     * 设置0表示女，1表示男
     *
     * @param sex 0表示女，1表示男
     */
    public void setSex(Byte sex) {
        this.sex = sex;
    }

    /**
     * 获取城市
     *
     * @return city - 城市
     */
    public String getCity() {
        return city;
    }

    /**
     * 设置城市
     *
     * @param city 城市
     */
    public void setCity(String city) {
        this.city = city;
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