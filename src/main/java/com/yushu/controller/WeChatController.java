package com.yushu.controller;

import com.yushu.config.WeChatConfig;
import com.yushu.contants.Contants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Controller
@RequestMapping("/api/video/weChat")
public class WeChatController {
    @Autowired
    WeChatConfig weChatConfig;

    /**
     * 拼装微信扫一扫登录url
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(value = "getLoginUrl",method = RequestMethod.GET)
    @ResponseBody
    public String getLoginUrl() throws UnsupportedEncodingException {
        String redirectUrl = URLEncoder.encode(weChatConfig.getRedirecturl(),"GBK");
        String url = String.format(Contants.WECHAT_OPEN_QRCODE_URL,weChatConfig.getAppid(),redirectUrl,"state");
        System.out.println(url);
        return url;
    }
}