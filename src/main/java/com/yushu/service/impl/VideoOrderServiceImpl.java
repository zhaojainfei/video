package com.yushu.service.impl;

import com.yushu.mapper.VideoOrderMapper;
import com.yushu.model.VideoOrder;
import com.yushu.service.VideoOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoOrderServiceImpl implements VideoOrderService {
    @Autowired
    VideoOrderMapper videoOrderMapper;

    @Override
    public List<VideoOrder> getList(VideoOrder videoOrder) {
        return videoOrderMapper.select(videoOrder);
    }

    @Override
    public VideoOrder get(VideoOrder videoOrder) {
        List<VideoOrder> list = this.getList(videoOrder);
        if(list.size() >0){
            return list.get(0);
        }
        return null;
    }

    @Override
    public Integer add(VideoOrder videoOrder) {
        return videoOrderMapper.insertSelective(videoOrder);
    }

    @Override
    public Integer update(VideoOrder videoOrder) {
        return videoOrderMapper.updateByPrimaryKeySelective(videoOrder);
    }
}
