package com.itrum.community.community.service.impl;

import com.itrum.community.community.domain.User;
import com.itrum.community.community.mapper.UserMapper;
import com.itrum.community.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void insert(User user) throws Exception {
        userMapper.insert(user);
    }
}
