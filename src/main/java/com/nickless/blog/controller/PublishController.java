package com.nickless.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * creat by nickless
 *
 * @Date 2020/1/3 20:22
 */
@Controller
public class PublishController {
    @GetMapping("/publish")
     public  String publish(){
         return "publish";
     }

}
