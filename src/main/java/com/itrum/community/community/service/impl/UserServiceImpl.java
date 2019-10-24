package com.itrum.community.community.service.impl;

import com.itrum.community.community.domain.User;
import com.itrum.community.community.domain.UserExample;
import com.itrum.community.community.mapper.UserMapper;
import com.itrum.community.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void insert(User user) throws Exception {
        userMapper.insert(user);
    }

    @Override
    public User findUserByAccountId(String accountId) throws Exception {
        UserExample example = new UserExample();
        example.createCriteria().andAccountIdEqualTo(accountId);
        List<User> users = userMapper.selectByExample(example);
        if (users.size()!=0) return users.get(0);
        return null;
    }

    @Override
    public User findUserByToken(String token) throws Exception {
        UserExample example = new UserExample();
        example.createCriteria().andTokenEqualTo(token);
        List<User> users = userMapper.selectByExample(example);
        if (users.size()!=0) return users.get(0);
        return null;
    }
}
