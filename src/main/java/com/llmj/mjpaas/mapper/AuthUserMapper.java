package com.llmj.mjpaas.mapper;

import com.llmj.mjpaas.model.AuthUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AuthUserMapper {
    //根据username和userpassword查询数据，老版本校验
    public int checkLogin(AuthUser authUser);

    /**
     * 获取密码
     */
    String getPassword(String username);

    /**
     * 获得角色权限
     */
    String getRole(String username);

    AuthUser getUserByName(String username);

}
