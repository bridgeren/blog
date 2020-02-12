package com.nickless.blog.controller;

import com.nickless.blog.dto.PaginationDto;
import com.nickless.blog.dto.QuestionDto;
import com.nickless.blog.mapper.QuestionMapper;
import com.nickless.blog.mapper.UserMapper;
import com.nickless.blog.model.Question;
import com.nickless.blog.model.User;
import com.nickless.blog.service.QuestionService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * creat by nickless
 *
 * @Date 2019/12/1 22:18
 */
@Controller
public class IndexController {
    @Autowired
    UserMapper userMapper;

    @Autowired
    QuestionService questionService;

    @GetMapping("/")
    public String greeting(HttpServletRequest request,
                           Model model,
                           @RequestParam(name = "page", defaultValue = "1") Integer page,
                           @RequestParam(name = "size", defaultValue = "5") Integer size) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null||cookies.equals("")) {
            return "index";
        }
        if(cookies!=null&&cookies.length!=0)
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
        PaginationDto pagination = questionService.list(page,size);
        model.addAttribute("pagination", pagination);
        return "index";
    }

}