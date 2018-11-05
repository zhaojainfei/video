package com.yushu.controller;

import com.alibaba.fastjson.JSONObject;
import com.yushu.config.WeChatConfig;
import com.yushu.contants.Contants;
import com.yushu.model.User;
import com.yushu.service.UserService;
import com.yushu.utils.HttpUtils;
import com.yushu.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;

@Controller
@RequestMapping("/api/video/weChat")
public class WeChatController {
    @Autowired
    WeChatConfig weChatConfig;
    @Autowired
    UserService userService;

    /**
     * 拼装微信扫一扫登录url
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(value = "getLoginUrl",method = RequestMethod.GET)
    @ResponseBody
    public String getLoginUrl() throws UnsupportedEncodingException {
        String redirectUrl = URLEncoder.encode(weChatConfig.getRedirecturl(),"GBK");
        String url = String.format(Contants.WECHAT_OPEN_QRCODE_URL,weChatConfig.getAppid(),redirectUrl,"/api/video/video/index");
        System.out.println(url);
        return url;
    }

    /**
     * 1.通过微信扫一扫获取code，回调到这个地址
     * 2.通过code去调取access_token
     * @param code
     * @param state
     * @param response
     */
    @RequestMapping(value = "weChatLoginCallBack",method = RequestMethod.GET)
    public String weChatLoginCallBack(@RequestParam(name = "code") String code, String state, HttpServletResponse response) {
        String url = String.format(Contants.WECHAT_OPEN_ACCESS_TOKEN_URL,weChatConfig.getAppid(),weChatConfig.getAppsecret(),code);
        String accessToken = HttpUtils.doGet(url);
        JSONObject jsonObject = JSONObject.parseObject(accessToken);
        String errcode = jsonObject.getString("errcode");
        if(StringUtils.isEmpty(errcode)){
            String openid = jsonObject.getString("openid");
            String access_token = jsonObject.getString("access_token");
            url = String.format(Contants.WECHAT_OPEN_USER_INFO,access_token,openid);
            String userInfo = HttpUtils.doGet(url);
            System.out.println(userInfo);

            User user = JSONObject.parseObject(userInfo, User.class);
            User user_temp = new User();
            user_temp.setOpenid(user.getOpenid());
            user_temp.setDeleteStatus(false);
            user_temp = userService.get(user_temp);
            if(user_temp == null){
                // 获取回来姓名是乱码，需要如下编码一下
                try{
                    user.setNickName(new String(user.getNickName().getBytes("ISO-8859-1"),"UTF-8"));
                    user.setCountry(new String(user.getCountry().getBytes("ISO-8859-1"),"UTF-8"));
                    user.setProvince(new String(user.getProvince().getBytes("ISO-8859-1"),"UTF-8"));
                    user.setCity(new String(user.getCity().getBytes("ISO-8859-1"),"UTF-8"));
                    user.setDeleteStatus(false);
                    user.setCreateTime(new Date());
                    userService.add(user);
                    user_temp = user;
                }catch(UnsupportedEncodingException ex){

                }
            }
            String token = JwtUtils.generateJwt(user_temp);
            return "redirect:"+state+"?token=" + token;
        }
        return "index";
    }
}