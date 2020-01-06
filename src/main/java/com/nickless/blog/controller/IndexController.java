package com.nickless.blog.controller;

import com.nickless.blog.mapper.UserMapper;
import com.nickless.blog.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * creat by nickless
 *
 * @Date 2019/12/1 22:18
 */
@Controller
public class IndexController {
    @Autowired
    UserMapper userMapper;

    @GetMapping("/")
    public String greeting(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if(cookies==null){
            return  "index";
        }
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("token")) {
                String token = cookie.getValue();
                User user = userMapper.findByToken(token);
                if (user != null) {
                    request.getSession().setAttribute("user", user);
                }
                break;
            }
        }
        return "index";
    }

}