package com.nickless.blog.controller;

import com.nickless.blog.dto.QuestionDto;
import com.nickless.blog.mapper.QuestionMapper;
import com.nickless.blog.model.Question;
import com.nickless.blog.model.User;
import com.nickless.blog.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * creat by nickless
 *
 * @Date 2020/1/3 20:22
 */
@Controller
public class PublishController {



    @Autowired
    private QuestionService questionService;

    @GetMapping("publish/{id}")
    public  String edit(@PathVariable(name="id") Integer id,
                                               Model model      ){
        QuestionDto question=questionService.getById(id);
        model.addAttribute("title",question.getTitle());
        model.addAttribute("description",question.getDescription());
        model.addAttribute("tag",question.getTag());
        model.addAttribute("id",question.getId());
        return "publish";
    }

    @GetMapping("/publish")
    public String publish() {
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("tag") String tag,
            @RequestParam("id") Integer id,
            HttpServletRequest request,
            Model model

    ) {
        model.addAttribute("title",title);
        model.addAttribute("description",description);
        model.addAttribute("tag",tag);
        if(title==null||title==""){
            model.addAttribute("error","标题不能为空");
            return  "publish";
        }
        if(description==null||description==""){
            model.addAttribute("error","描述不能为空");
            return  "publish";
        }
        if(tag==null||tag==""){
            model.addAttribute("error","标签不能为空");
            return  "publish";
        }
        User user=(User) request.getSession().getAttribute("user");
        if(user==null){
            model.addAttribute("error","用户未登陆");
            return  "publish";
        }
        Question question = new Question();

        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setCreator(user.getId());

        question.setId(id);
        questionService.createOrUpdate(question);



        return "redirect:/";
    }


}
