package com.llmj.mjpaas.shiro;

import com.llmj.mjpaas.mapper.AuthResourcesRoleMapper;
import com.llmj.mjpaas.mapper.AuthRoleMapper;
import com.llmj.mjpaas.model.AuthResourcesRole;
import com.llmj.mjpaas.model.AuthRole;
import com.llmj.mjpaas.model.AuthUser;
import com.llmj.mjpaas.service.AuthUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.omg.CORBA.portable.UnknownException;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.rmi.activation.UnknownObjectException;
import java.util.List;

/**
 * @author jinbin
 * @date 2017-12-01 21:25
 */
public class MyShiroRealm extends AuthorizingRealm {

    @Resource
    private AuthResourcesRoleMapper authResourcesRoleMapper;
    @Resource
    private AuthRoleMapper authRoleMapper;
    @Resource
    private AuthUserService authUserService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
        System.out.println("权限配置-->MyShiroRealm.doGetAuthorizationInfo()");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        AuthUser authUser = (AuthUser) principal.getPrimaryPrincipal();

        AuthRole role = new AuthRole();
        role.setUsername(authUser.getUsername());
        List<AuthRole> authRoleList = authRoleMapper.findRolesByUserName(role);

        AuthResourcesRole authResourcesRole = new AuthResourcesRole();
        authResourcesRole.setUsername(authUser.getUsername());
        List<AuthResourcesRole> authResourcesRolesList = authResourcesRoleMapper.
                findResourcesByUserName(authResourcesRole);
        for (AuthRole roleC : authRoleList) {
            authorizationInfo.addRole(roleC.getName());
        }
        for (AuthResourcesRole authResourcesC : authResourcesRolesList) {
            authorizationInfo.addStringPermission(authResourcesC.getResurl());
        }
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //获取用户的输入的账号.
        UsernamePasswordToken userpasswordToken = (UsernamePasswordToken) token;//这边是界面的登陆数据，将数据封装成token
        String username = userpasswordToken.getUsername();
        char[] password = userpasswordToken.getPassword();
        if (username == null || username == "") {//如果用户名为空，则直接返回空数据
            throw new UnknownAccountException("用户名不可为空");
        }
        AuthUser user = authUserService.getUserByName(username);
        return new SimpleAuthenticationInfo(user, user.getUserpassword(), this.getClass().getName());

    }
}
