package com.nickless.blog.controller;

import com.nickless.blog.dto.PaginationDto;
import com.nickless.blog.model.User;
import com.nickless.blog.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * creat by nickless
 *
 * @Date 2020/2/11 18:20
 */
@Controller
public class ProfileController {


    @Autowired
    private QuestionService questionService;
    private PaginationDto paginationDto;

    @GetMapping("/profile/{action}")
    public String profile(HttpServletRequest request,
                          @PathVariable(name = "action") String action,
                          Model model,
                          @RequestParam(name = "page", defaultValue = "1") Integer page,
                          @RequestParam(name = "size", defaultValue = "5") Integer size) {

       User user=(User) request.getSession().getAttribute("user");
       if(user==null){
           return  "redirect:/";
       }
        if ("questions".equals(action)) {
            model.addAttribute("section", "questions");
            model.addAttribute("sectionName", "我的提问"); // 右边的tag
        } else if ("replies".equals(action)) {
            model.addAttribute("section", "replies");
            model.addAttribute("sectionName", "最新回复");
        }

        PaginationDto paginationDto= questionService.list(user.getId(), page, size);
        model.addAttribute("pagination", paginationDto);

        return "profile";
    }

}
