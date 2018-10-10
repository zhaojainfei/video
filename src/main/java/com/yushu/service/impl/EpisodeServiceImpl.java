package com.yushu.service.impl;

import com.yushu.mapper.EpisodeMapper;
import com.yushu.model.Episode;
import com.yushu.service.EpisodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EpisodeServiceImpl implements EpisodeService {
    @Autowired
    EpisodeMapper episodeMapper;

    @Override
    public List<Episode> getList(Episode episode) {
        return episodeMapper.select(episode);
    }

    @Override
    public Episode get(Episode episode) {
        List<Episode> list = this.getList(episode);
        if(list.size() >0){
            return list.get(0);
        }
        return null;
    }

    @Override
    public Integer add(Episode episode) {
        return episodeMapper.insertSelective(episode);
    }

    @Override
    public Integer update(Episode episode) {
        return episodeMapper.updateByPrimaryKeySelective(episode);
    }
}
