package com.yushu.service.impl;

import com.yushu.mapper.CommentMapper;
import com.yushu.model.Comment;
import com.yushu.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentMapper commentMapper;

    @Override
    public List<Comment> getList(Comment comment) {
        return commentMapper.select(comment);
    }

    @Override
    public Comment get(Comment comment) {
        List<Comment> list = this.getList(comment);
        if(list.size() >0){
            return list.get(0);
        }
        return null;
    }

    @Override
    public Integer add(Comment comment) {
        return commentMapper.insertSelective(comment);
    }

    @Override
    public Integer update(Comment comment) {
        return commentMapper.updateByPrimaryKeySelective(comment);
    }
}
