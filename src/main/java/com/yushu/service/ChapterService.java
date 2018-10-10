package com.yushu.service;

import com.yushu.model.Chapter;

import java.util.List;

public interface ChapterService {
    List<Chapter> getList(Chapter chapter);
    Chapter get(Chapter chapter);
    Integer add(Chapter chapter);
    Integer update(Chapter chapter);
}
