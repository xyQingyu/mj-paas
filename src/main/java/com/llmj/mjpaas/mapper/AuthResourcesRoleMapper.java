package com.llmj.mjpaas.mapper;

import com.llmj.mjpaas.model.AuthResourcesRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface AuthResourcesRoleMapper {
    public List<AuthResourcesRole> findResourcesByUserName(AuthResourcesRole  authResourcesRole);
}
