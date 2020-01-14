package com.nickless.blog.service;

import com.nickless.blog.dto.PaginationDto;
import com.nickless.blog.dto.QuestionDto;
import com.nickless.blog.mapper.QuestionMapper;
import com.nickless.blog.mapper.UserMapper;
import com.nickless.blog.model.Question;
import com.nickless.blog.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * creat by nickless
 *
 * @Date 2020/1/7 22:07
 */
@Service
public class QuestionService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionMapper questionMapper;

    public PaginationDto list(Integer page, Integer size) {

        PaginationDto paginationDto = new PaginationDto();
        Integer totalcount = questionMapper.count(); // 总条数
        paginationDto.setPagination(totalcount, page, size);
        int totalPage=paginationDto.getTotalPage();
        if (page < 1) {
            page = 1;
        }
        if (page > totalPage) {
            page = totalPage;
        }

        Integer offset = size * (page - 1);
        List<QuestionDto> questionDtoList = new ArrayList<>();
        List<Question> questionList = questionMapper.list(offset, size);

        for (Question question : questionList) {
            User user = userMapper.findById(question.getCreator());
            QuestionDto questionDto = new QuestionDto();
            BeanUtils.copyProperties(question, questionDto);
            questionDto.setUser(user);
            questionDtoList.add(questionDto);
        }
        paginationDto.setQuestions(questionDtoList);


        return paginationDto;
    }
}
