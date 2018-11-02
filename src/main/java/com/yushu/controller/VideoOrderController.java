package com.yushu.controller;

import com.yushu.model.User;
import com.yushu.model.Video;
import com.yushu.model.VideoOrder;
import com.yushu.service.UserService;
import com.yushu.service.VideoOrderService;
import com.yushu.service.VideoService;
import com.yushu.utils.CommonUtils;
import com.yushu.utils.IpUtils;
import com.yushu.utils.WXPayUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.SortedMap;
import java.util.TreeMap;

@RestController
@RequestMapping("/api/video/videoOrder")
public class VideoOrderController {
    @Autowired
    VideoService videoService;
    @Autowired
    UserService userService;
    @Autowired
    VideoOrderService videoOrderService;

    @Value("${wxpay.mchid}")
    private String wxpayMchid;
    @Value("${wxpay.key}")
    private String wxpayKey;
    @Value("${wxpay.redirect_url}")
    private String wxpayRedirecturl;
    @Value("${wxpay.appid}")
    private String wxpayAppid;
    /**
     * 生成订单
     */

    @RequestMapping(value = "generateOrder")
    public void generateOrder(Integer userId, Integer videoId, HttpServletRequest request)throws Exception{
        Video video =  new Video();
        video.setId(videoId);
        video.setDeleteStatus(false);
        video = videoService.get(video);

        User user = new User();
        user.setId(userId);
        user.setDeleteStatus(false);
        user = userService.get(user);

        VideoOrder videoOrder = new VideoOrder();
        videoOrder.setTotalFee(video.getPrice());
        videoOrder.setVideoImg(video.getCoverImg());
        videoOrder.setVideoTitle(video.getTitle());
        videoOrder.setCreateTime(new Date());
        videoOrder.setVideoId(video.getId());
        videoOrder.setState(0);
        videoOrder.setUserId(user.getId());
        videoOrder.setHeadImg(user.getHeadimgUrl());
        videoOrder.setNickname(user.getNickName());
        videoOrder.setDeleteStatus(false);
        videoOrder.setIp(IpUtils.getIpAddr(request));
        videoOrder.setOutTradeNo(CommonUtils.generateUUID());
        this.unifiedOrder(videoOrder);
        videoOrderService.add(videoOrder);
    }

    private String unifiedOrder(VideoOrder videoOrder) throws Exception{
        SortedMap map = new TreeMap();
        // 公众账号ID
        map.put("appid",this.wxpayAppid);
        // 商户号
        map.put("mch_id",this.wxpayMchid);
        // 随机字符串
        map.put("nonce_str",CommonUtils.generateUUID());
        // 商品描述
        map.put("body",videoOrder.getVideoTitle());
        // 商户订单号
        map.put("out_trade_no",videoOrder.getOutTradeNo());
        // 标价金额
        map.put("total_fee",videoOrder.getTotalFee().toString());
        // 终端IP - 网页支付提交用户端ip
        map.put("spbill_create_ip",videoOrder.getIp());
        // 异步接收微信支付结果通知的回调地址，通知url必须为外网可访问的url，不能携带参数。
        map.put("notify_url",this.wxpayRedirecturl);
        // 扫码登录方式
        map.put("trade_type","NATIVE");
        // 签名
        map.put("sign", WXPayUtil.createSign(map,this.wxpayKey));
        String payXml = WXPayUtil.mapToXml(map);
        System.out.println(payXml);
        // https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=20_1 验证签名
        return "";
    }
}
