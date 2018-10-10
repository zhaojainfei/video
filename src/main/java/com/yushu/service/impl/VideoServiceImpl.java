package com.yushu.service.impl;

import com.yushu.mapper.VideoMapper;
import com.yushu.model.Video;
import com.yushu.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoServiceImpl implements VideoService {
    @Autowired
    VideoMapper videoMapper;

    @Override
    public List<Video> getList(Video video) {
        return videoMapper.select(video);
    }

    @Override
    public Video get(Video video) {
        List<Video> list = this.getList(video);
        if(list.size() >0){
            return list.get(0);
        }
        return null;
    }

    @Override
    public Integer add(Video video) {
        return videoMapper.insertSelective(video);
    }

    @Override
    public Integer update(Video video) {
        return videoMapper.updateByPrimaryKeySelective(video);
    }
}
