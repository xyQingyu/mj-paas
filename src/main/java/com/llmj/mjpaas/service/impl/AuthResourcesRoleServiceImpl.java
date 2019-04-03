package com.llmj.mjpaas.service.impl;

import com.llmj.mjpaas.mapper.AuthResourcesRoleMapper;
import com.llmj.mjpaas.model.AuthResourcesRole;
import com.llmj.mjpaas.service.AuthResourcesRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AuthResourcesRoleServiceImpl implements AuthResourcesRoleService {
    @Resource
    private AuthResourcesRoleMapper authResourcesRoleMapper;

    @Override
    public List<AuthResourcesRole> findResourcesByUserName(AuthResourcesRole authResourcesRole) {
        return authResourcesRoleMapper.findResourcesByUserName(authResourcesRole);
    }
}
