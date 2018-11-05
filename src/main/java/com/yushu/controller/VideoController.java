package com.yushu.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yushu.model.Video;
import com.yushu.service.VideoService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api/video/video")
public class VideoController {
    @Autowired
    VideoService videoService;

    @RequestMapping(value = "index")
    public String index(Model model, HttpServletRequest request){
        String openId = (String)request.getAttribute("openId");
        String nickName = (String)request.getAttribute("nickName");
        String headImg = (String)request.getAttribute("headImg");

        model.addAttribute("openId",openId);
        model.addAttribute("nickName",nickName);
        model.addAttribute("headImg",headImg);
        return "video/index";
    }

    @RequestMapping(value = "getList",method = RequestMethod.GET)
    @ResponseBody
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
    @ResponseBody
    public Object getPageList(@RequestParam(name = "pageNum",required = true) Integer pageNum, @RequestParam(name = "pageSize",required = true)Integer pageSize){
        PageHelper.startPage(pageNum,pageSize);
        Video video = new Video();
        video.setDeleteStatus(false);
        List<Video> list = videoService.getList(video);
        PageInfo<Video> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @RequestMapping(value = "getById",method = RequestMethod.GET)
    @ResponseBody
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
