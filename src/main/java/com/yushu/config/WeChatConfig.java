package com.yushu.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * 微信配置
 */
@Configuration
public class WeChatConfig {
    @Value("${wxpay.appid}")
    private String appid;
    public void setAppid(String appid) {
        this.appid = appid;
    }
    public String getAppid() {
        return appid;
    }

    @Value("${wxpay.appsecret}")
    private String appsecret;
    public void setAppsecret(String appsecret) {
        this.appsecret = appsecret;
    }
    public String getAppsecret() {
        return appsecret;
    }
}
