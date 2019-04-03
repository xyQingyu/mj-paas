package com.llmj.mjpaas.service.impl;

import com.llmj.mjpaas.mapper.AuthUserMapper;
import com.llmj.mjpaas.model.AuthUser;
import com.llmj.mjpaas.service.AuthUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AuthUserServiceImpl implements AuthUserService {
    @Resource
    private AuthUserMapper authUserMapper;

    @Override
    public int checkLogin(AuthUser authUser) {
        return authUserMapper.checkLogin(authUser);
    }

    @Override
    public AuthUser getUserByName(String username) {
        return authUserMapper.getUserByName(username);
    }
}
