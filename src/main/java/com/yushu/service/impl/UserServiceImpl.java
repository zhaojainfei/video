package com.yushu.service.impl;

import com.yushu.mapper.UserMapper;
import com.yushu.model.User;
import com.yushu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public List<User> getList(User user) {
        return userMapper.select(user);
    }

    @Override
    public User get(User user) {
        List<User> list = this.getList(user);
        if(list.size() >0){
            return list.get(0);
        }
        return null;
    }

    @Override
    public Integer add(User user) {
        return userMapper.insertSelective(user);
    }

    @Override
    public Integer update(User user) {
        return userMapper.updateByPrimaryKeySelective(user);
    }
}
