package com.yushu.service;

import com.yushu.model.User;

import java.util.List;

public interface UserService {
    List<User> getList(User user);
    User get(User user);
    Integer add(User user);
    Integer update(User user);
}
