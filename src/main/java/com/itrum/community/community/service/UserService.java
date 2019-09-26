package com.itrum.community.community.service;

import com.itrum.community.community.domain.User;

public interface UserService {
    void insert(User user) throws Exception;

    User findUserByAccountId(String accountId)  throws Exception;

    User findUserByToken(String token) throws Exception;
}
