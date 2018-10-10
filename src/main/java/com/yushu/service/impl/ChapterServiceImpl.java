package com.yushu.service.impl;

import com.yushu.mapper.ChapterMapper;
import com.yushu.model.Chapter;
import com.yushu.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChapterServiceImpl implements ChapterService {
    @Autowired
    ChapterMapper chapterMapper;

    @Override
    public List<Chapter> getList(Chapter chapter) {
        return chapterMapper.select(chapter);
    }

    @Override
    public Chapter get(Chapter chapter) {
        List<Chapter> list = this.getList(chapter);
        if(list.size() >0){
            return list.get(0);
        }
        return null;
    }

    @Override
    public Integer add(Chapter chapter) {
        return chapterMapper.insertSelective(chapter);
    }

    @Override
    public Integer update(Chapter chapter) {
        return chapterMapper.updateByPrimaryKeySelective(chapter);
    }
}
