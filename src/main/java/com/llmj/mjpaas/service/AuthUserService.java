package com.llmj.mjpaas.service;

import com.llmj.mjpaas.model.AuthUser;

public interface AuthUserService {
    //根据username和userpassword查询数据
    public int checkLogin(AuthUser authUser);

    AuthUser getUserByName(String username);
}
