package org.geekbang.wangzhi.week07.service.impl;

import org.geekbang.wangzhi.week07.mapper.UserMapper;
import org.geekbang.wangzhi.week07.model.User;
import org.geekbang.wangzhi.week07.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUser(Long userId) {
        Assert.notNull(userId,"user id can not be null");
        return userMapper.selectByPrimaryKey(userId);
    }
}
