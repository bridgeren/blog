package com.nickless.blog.controller;

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

    @GetMapping("/")
    public String greeting() {

        return "index";
    }

}