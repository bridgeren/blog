package com.nickless.blog.controller;

import com.nickless.blog.dto.PaginationDto;
import com.nickless.blog.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * creat by nickless
 *
 * @Date 2019/12/1 22:18
 */
@Controller
public class IndexController {


    @Autowired
    QuestionService questionService;

    @GetMapping("/")
    public String greeting(
                           Model model,
                           @RequestParam(name = "page", defaultValue = "1") Integer page,
                           @RequestParam(name = "size", defaultValue = "5") Integer size) {

        PaginationDto pagination = questionService.list(page,size);
        model.addAttribute("pagination", pagination);
        return "index";
    }

}