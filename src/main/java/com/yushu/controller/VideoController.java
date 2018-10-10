package com.yushu.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yushu.model.Video;
import com.yushu.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/video/video")
public class VideoController {
    @Autowired
    VideoService videoService;

    @RequestMapping(value = "getList",method = RequestMethod.GET)
    public Object getList(){
        Video video = new Video();
        video.setDeleteStatus(false);
        List<Video> list = videoService.getList(video);
        Map<String,Object> map = new HashMap<>();
        map.put("code","0");
        map.put("data",list);
        return map;
    }

    @RequestMapping(value = "getPageList",method = RequestMethod.GET)
    public Object getPageList(@RequestParam(name = "pageNum",required = true) Integer pageNum, @RequestParam(name = "pageSize",required = true)Integer pageSize){
        PageHelper.startPage(pageNum,pageSize);
        Video video = new Video();
        video.setDeleteStatus(false);
        List<Video> list = videoService.getList(video);
        PageInfo<Video> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @RequestMapping(value = "getById",method = RequestMethod.GET)
    public Object getById(Integer id){
        Video video = new Video();
        video.setId(id);
        video.setDeleteStatus(false);
        video = videoService.get(video);
        Map<String,Object> map = new HashMap<>();
        map.put("code","0");
        map.put("data",video);
        return map;
    }
}
