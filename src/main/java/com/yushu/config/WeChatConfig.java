package com.yushu.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * 微信配置
 */
@Configuration
public class WeChatConfig {
    @Value("${wxopen.appid}")
    private String appid;
    public void setAppid(String appid) {
        this.appid = appid;
    }
    public String getAppid() {
        return appid;
    }

    @Value("${wxopen.appsecret}")
    private String appsecret;
    public void setAppsecret(String appsecret) {
        this.appsecret = appsecret;
    }
    public String getAppsecret() {
        return appsecret;
    }

    @Value("${wxopen.redirect_url}")
    private String redirecturl;
    public void setRedirecturl(String redirecturl) {
        this.redirecturl = redirecturl;
    }
    public String getRedirecturl() {
        return redirecturl;
    }
}
