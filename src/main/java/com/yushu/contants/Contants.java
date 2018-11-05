package com.yushu.contants;

public class Contants {
    /**
     * 微信开放平台二维码连接
     */
    public static final String WECHAT_OPEN_QRCODE_URL = "https://open.weixin.qq.com/connect/qrconnect?appid=%s&redirect_uri=%s&response_type=code&scope=snsapi_login&state=%s#wechat_redirect";

    /**
     * 微信开放平台获取access_token地址
     */
    public static final String WECHAT_OPEN_ACCESS_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code";

    /**
     * 微信开放平台获取用户信息
     */
    public static final String WECHAT_OPEN_USER_INFO = "https://api.weixin.qq.com/sns/userinfo?access_token=%s&openid=%s&lang=zh_CN";

    /**
     * 微信统一下单URL地址
     */
    public static final String UNIFIED_ORDER_URL = "http://api.xdclass.net/pay/unifiedorder";
}
