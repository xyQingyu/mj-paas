package com.llmj.mjpaas.controller;

import com.alibaba.fastjson.JSON;
import com.sun.javafx.collections.MappingChange;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("")
@Controller
public class BasicErrorController implements ErrorController {

    private static final String ERROR_PATH = "/error";

    @RequestMapping(ERROR_PATH)
    public String error() {
        return "error";
    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }
}