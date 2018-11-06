package com.yushu.controller;

import com.alibaba.fastjson.JSONObject;
import com.yushu.config.WeChatConfig;
import com.yushu.contants.Contants;
import com.yushu.model.User;
import com.yushu.model.VideoOrder;
import com.yushu.service.UserService;
import com.yushu.service.VideoOrderService;
import com.yushu.utils.CommonUtils;
import com.yushu.utils.HttpUtils;
import com.yushu.utils.JwtUtils;
import com.yushu.utils.WXPayUtil;
import io.jsonwebtoken.Claims;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Map;
import java.util.SortedMap;

@Controller
@RequestMapping("/api/video/weChat")
public class WeChatController {
    @Autowired
    WeChatConfig weChatConfig;
    @Autowired
    UserService userService;
    @Autowired
    VideoOrderService videoOrderService;

    @Value("${wxpay.key}")
    private String wxpayKey;

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
    @RequestMapping(value = "loginCallBack",method = RequestMethod.GET)
    public String loginCallBack(@RequestParam(name = "code") String code, String state, HttpServletResponse response) {
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

    @RequestMapping(value = "/orderCallBack",method = RequestMethod.POST)
    public void orderCallBack(HttpServletRequest request,HttpServletResponse response) throws Exception{
        InputStream inputStream = request.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = bufferedReader.readLine())!= null){
            sb.append(line);
        }
        bufferedReader.close();
        inputStream.close();
        Map<String,String> map = WXPayUtil.xmlToMap(sb.toString());
        System.out.println(sb.toString());
        SortedMap sortedMap = CommonUtils.mapToSortedMap(map);

        // 验证一遍签名(用他回传的参数做值计算出来的签名)
        if(WXPayUtil.isCorrectSign(sortedMap,this.wxpayKey)){
            if(sortedMap.get("result_code").equals("SUCCESS")){
                VideoOrder videoOrder = new VideoOrder();
                videoOrder.setOutTradeNo(sortedMap.get("out_trade_no").toString());
                videoOrder = videoOrderService.get(videoOrder);
                videoOrder.setOpenid(sortedMap.get("openid").toString());
                videoOrder.setNotifyTime(new Date());
                videoOrder.setState(1);
                if(videoOrderService.update(videoOrder) > 0){
                    response.setContentType("text/xml");
                    response.getWriter().println("success");
                    return;
                }
            }
            else{
                response.setContentType("text/xml");
                response.getWriter().println("success");
            }
        }
    }
}