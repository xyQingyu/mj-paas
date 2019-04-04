package com.llmj.mjpaas.controller;

import com.llmj.mjpaas.model.AuthUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/")
public class LoginController {
    /**
     * 跳转到登录
     *
     * @return
     */
    @RequestMapping(value = "login")
    public String getString() {
        return "login";
    }


}
