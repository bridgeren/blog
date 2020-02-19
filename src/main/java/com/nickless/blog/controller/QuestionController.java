package com.nickless.blog.controller;

import com.nickless.blog.dto.QuestionDto;
import com.nickless.blog.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * creat by nickless
 *
 * @Date 2020/2/19 15:56
 */
@Controller
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @GetMapping("/question/{id}")
    public String question( @PathVariable(name = "id") Integer id,
                            Model model
                             ){
        QuestionDto  questionDto=questionService.getById(id);
        model.addAttribute("question",questionDto);
        return  "question";
    }

}
