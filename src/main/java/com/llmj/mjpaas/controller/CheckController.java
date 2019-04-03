package com.llmj.mjpaas.controller;

import com.llmj.mjpaas.model.AuthResourcesRole;
import com.llmj.mjpaas.model.AuthUser;
import com.llmj.mjpaas.service.AuthResourcesRoleService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
public class CheckController {
    @Resource
    private AuthResourcesRoleService authResourcesRoleService;
    /*
     * 将登陆的用户/密码传入UsernamePasswordToken，当调用subject.login(token)开始，调用Relam的doGetAuthenticationInfo方法，开始密码验证
     * 此时这个时候执行我们自己编写的CredentialMatcher（密码匹配器），执行doCredentialsMatch方法，具体的密码比较实现在这实现
     *
     * */
    @RequestMapping(value = { "/loginUser" },
            method = { RequestMethod.POST }, produces="application/json;charset=UTF-8")
    @ResponseBody
    public String loginUser(String username,
                            String password,
                            HttpSession session) {
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();
        try {
            System.out.println("获取到信息，开始验证！！");
            subject.login(token);//登陆成功的话，放到session中
            AuthUser user = (AuthUser) subject.getPrincipal();
            session.setAttribute("user", user);
            session.setAttribute("userName",user.getUsername());

            return  "{\"result\":\"success\",\"msg\":\"登录成功！\"}";
        } catch (Exception e) {
            return  "{\"result\":\"fail\",\"msg\":\"用户或密码错误！\"}";
        }
    }


    /**
     * 跳转到首页
     * @return
     */
    @RequestMapping(value = "main")
    public String getMain(HttpSession session, HttpServletResponse response){
        //存放当前登录人员的权限菜单信息
        String userName = "";
        //存放cookie中的权限菜单
        String cookieResources = "";

        if(session != null){
            userName = session.getAttribute("userName").toString();
        }
        //查询当前登录人的菜单资源
        AuthResourcesRole resources = new AuthResourcesRole();
        resources.setUsername(userName);
        List<AuthResourcesRole>  authResourcesRoleList = authResourcesRoleService.findResourcesByUserName(resources);
       //将菜单资源转换为jsonArray
        if(authResourcesRoleList.size() > 0){
            cookieResources = getResourcesStr(authResourcesRoleList);
        }
        //设置菜单进入cookie中
        Cookie cookie=new Cookie("resourcesList",cookieResources);
        cookie.setPath("/");
        //关闭浏览器失效
        cookie.setMaxAge(-1);
        response.addCookie(cookie);
        return "main";

    }

    /**
     * 将菜单转换成jsonArray字符串
     * */
    public String getResourcesStr( List<AuthResourcesRole>  authResourcesRoleList){
        String str = "";
        for(AuthResourcesRole resourcesRole : authResourcesRoleList){
            String id = resourcesRole.getId();
            String name = resourcesRole.getName();
            String parentId = resourcesRole.getParentid();
            String type = resourcesRole.getType();
            String resurl = resourcesRole.getResurl();
            String level = resourcesRole.getLevel();
            String paixu = resourcesRole.getPaixu();
            //不可以用,号，cookie存值会报错，页面获取时，用replace方法转换
            str = str + "{'id':'"+id+"'-'name':'"+name+"'"
                    +"-'parentId':'"+parentId+"'-'type':'"+type+"'-'resurl':'"+resurl+"'"
                    +"-'level':'"+level+"'-'paixu':'"+paixu+"'}-";
        }
        if(str.length() > 0){
            str ="[" + str.substring(0,str.length()-1) + "]";
        }
        return str;
    }

    @RequiresPermissions("403") //加入注解，执行角色权限认证
    @RequestMapping("/403")
    public String login() {
        return "403";
    }

    @RequiresPermissions("404") //加入注解，执行角色权限认证
    @RequestMapping("/404")
    public String notFound() {
        return "error";
    }

    @RequestMapping("/logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();//取出当前验证主体
        if (subject != null) {
            subject.logout();//不为空，执行一次logout的操作，将session全部清空
        }
        return "login";
    }

}
