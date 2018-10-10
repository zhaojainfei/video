package com.yushu.service;

import com.yushu.model.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> getList(Comment comment);
    Comment get(Comment comment);
    Integer add(Comment comment);
    Integer update(Comment comment);
}
