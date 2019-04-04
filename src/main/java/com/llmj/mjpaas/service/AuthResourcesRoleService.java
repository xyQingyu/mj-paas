package com.llmj.mjpaas.service;

import com.llmj.mjpaas.model.AuthResourcesRole;

import java.util.List;

public interface AuthResourcesRoleService {
    public List<AuthResourcesRole> findResourcesByUserName(AuthResourcesRole authResourcesRole);

}
