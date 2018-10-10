package com.yushu.service;

import com.yushu.model.Video;

import java.util.List;

public interface VideoService {
    List<Video> getList(Video video);
    Video get(Video video);
    Integer add(Video video);
    Integer update(Video video);
}
