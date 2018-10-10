package com.yushu.service;

import com.yushu.model.VideoOrder;

import java.util.List;

public interface VideoOrderService {
    List<VideoOrder> getList(VideoOrder videoOrder);
    VideoOrder get(VideoOrder videoOrder);
    Integer add(VideoOrder videoOrder);
    Integer update(VideoOrder videoOrder);
}
