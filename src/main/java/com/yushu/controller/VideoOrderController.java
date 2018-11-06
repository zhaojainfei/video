package com.yushu.controller;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.yushu.contants.Contants;
import com.yushu.model.User;
import com.yushu.model.Video;
import com.yushu.model.VideoOrder;
import com.yushu.service.UserService;
import com.yushu.service.VideoOrderService;
import com.yushu.service.VideoService;
import com.yushu.utils.CommonUtils;
import com.yushu.utils.HttpUtils;
import com.yushu.utils.IpUtils;
import com.yushu.utils.WXPayUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.*;

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
    @RequestMapping(value = "generateOrder",method = RequestMethod.GET)
    // 事物的传播行为:
    @Transactional(propagation = Propagation.REQUIRED)
    public void generateOrder(Integer userId, Integer videoId, HttpServletRequest request, HttpServletResponse response)throws Exception{
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
        videoOrderService.add(videoOrder);
        String payUrl = this.unifiedOrder(videoOrder);

        try{
            // 生成支付链接二维码
            Map<EncodeHintType,Object> map = new HashMap<>();
            map.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            map.put(EncodeHintType.CHARACTER_SET,"UTF-8");
            BitMatrix bitMatrix = new MultiFormatWriter().encode(payUrl, BarcodeFormat.QR_CODE,400,400,map);
            // 用流的方式写入到输出流中
            MatrixToImageWriter.writeToStream(bitMatrix,"png",response.getOutputStream());

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 统一下单
     * @param videoOrder
     * @return
     * @throws Exception
     */
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
        map.put("spbill_create_ip","164.52.12.131");
        // 异步接收微信支付结果通知的回调地址，通知url必须为外网可访问的url，不能携带参数。
        map.put("notify_url",this.wxpayRedirecturl);
        // 扫码登录方式
        map.put("trade_type","NATIVE");
        // 签名
        map.put("sign", WXPayUtil.createSign(map,this.wxpayKey));
        String payXml = WXPayUtil.mapToXml(map);
        String payResult = HttpUtils.sendPost(Contants.UNIFIED_ORDER_URL,payXml);
        System.out.println(payResult);
        Map<String,String> map_unifiedResult = WXPayUtil.xmlToMap(payResult);
        if(map_unifiedResult != null){
            return map_unifiedResult.get("code_url");
        }
        return "";
    }
}
