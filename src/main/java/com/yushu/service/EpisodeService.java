package com.yushu.service;

import com.yushu.model.Episode;

import java.util.List;

public interface EpisodeService {
    List<Episode> getList(Episode episode);
    Episode get(Episode episode);
    Integer add(Episode episode);
    Integer update(Episode episode);
}
