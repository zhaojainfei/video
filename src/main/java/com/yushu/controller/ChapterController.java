package com.yushu.controller;

import com.github.pagehelper.PageHelper;
import com.yushu.model.Chapter;
import com.yushu.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/video/chapter")
public class ChapterController {
    @Autowired
    ChapterService chapterService;

    @RequestMapping(value = "getList",method = RequestMethod.GET)
    public Object getList(){
        Chapter chapter = new Chapter();
        chapter.setDeleteStatus(false);
        List<Chapter> list = chapterService.getList(chapter);
        Map<String,Object> map = new HashMap<>();
        map.put("code","0");
        map.put("data",list);
        return map;
    }

    @RequestMapping(value = "getPageList",method = RequestMethod.GET)
    public Object getPageList(Integer pageNum,Integer pageSize){
        PageHelper.startPage(pageNum,pageSize);
       return this.getList();
    }

    @RequestMapping(value = "getById",method = RequestMethod.GET)
    public Object getById(Integer id){
        Chapter chapter = new Chapter();
        chapter.setId(id);
        chapter.setDeleteStatus(false);
        chapter = chapterService.get(chapter);
        Map<String,Object> map = new HashMap<>();
        map.put("code","0");
        map.put("data",chapter);
        return map;
    }
}
