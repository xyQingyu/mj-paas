package com.llmj.mjpaas.mapper;

import com.llmj.mjpaas.model.AuthRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface AuthRoleMapper {
    List<AuthRole> findRolesByUserName(AuthRole authRole);
}
