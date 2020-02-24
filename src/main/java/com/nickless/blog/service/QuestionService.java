package com.nickless.blog.service;

import com.nickless.blog.dto.PaginationDto;
import com.nickless.blog.dto.QuestionDto;
import com.nickless.blog.mapper.QuestionMapper;
import com.nickless.blog.mapper.UserMapper;
import com.nickless.blog.model.Question;
import com.nickless.blog.model.QuestionExample;
import com.nickless.blog.model.User;
import org.apache.ibatis.session.RowBounds;
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
        Integer totalcount = (int)questionMapper.countByExample(new QuestionExample()); // 总条数

        Integer totalPage;

        if (totalcount % size == 0) {
            totalPage = totalcount / size;
        } else {
            totalPage = totalcount / size + 1;
        }


        if (page < 1) {
            page = 1;
        }
        if (page > totalPage) {
            page = totalPage;
        }
        paginationDto.setPagination(totalPage, page);
        Integer offset = size * (page - 1);
        if(offset<0)
            offset=0;
        List<QuestionDto> questionDtoList = new ArrayList<>();
        List<Question>  questionList=questionMapper.selectByExampleWithBLOBsWithRowbounds(new QuestionExample(),new RowBounds(offset,size));


        for (Question question : questionList) {
            User user = userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDto questionDto = new QuestionDto();
            BeanUtils.copyProperties(question, questionDto);
            questionDto.setUser(user);
            questionDtoList.add(questionDto);
        }
        paginationDto.setQuestions(questionDtoList);


        return paginationDto;
    }

    public PaginationDto list(Integer userId, Integer page, Integer size) {
        PaginationDto paginationDto = new PaginationDto();
        Integer totalPage;
        QuestionExample questionExample=new QuestionExample();
        questionExample.createCriteria()
                .andCreatorEqualTo(userId);
        Integer totalcount = (int)questionMapper.countByExample(new QuestionExample()); // 总条数
        if (totalcount % size == 0) {
            totalPage = totalcount / size;
        } else {
            totalPage = totalcount / size + 1;
        }


        if (page < 1) {
            page = 1;
        }
        if (page > totalPage) {
            page = totalPage;
        }
        paginationDto.setPagination(totalPage, page);
        Integer offset = size * (page - 1);
        List<QuestionDto> questionDtoList = new ArrayList<>();
        QuestionExample example=new QuestionExample();
        example.createCriteria()
                .andCreatorEqualTo(userId);

        List<Question>  questionList=questionMapper.selectByExampleWithBLOBsWithRowbounds(example,new RowBounds(offset,size));


        for (Question question : questionList) {
            User user = userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDto questionDto = new QuestionDto();
            BeanUtils.copyProperties(question, questionDto);
            questionDto.setUser(user);
            questionDtoList.add(questionDto);
        }
        paginationDto.setQuestions(questionDtoList);


        return paginationDto;
    }

    public QuestionDto getById(Integer id) {
        Question question = questionMapper.selectByPrimaryKey(id);
        QuestionDto questionDto = new QuestionDto();
        BeanUtils.copyProperties(question, questionDto);
        User user = userMapper.selectByPrimaryKey(question.getCreator());
        questionDto.setUser(user);
        return questionDto;

    }

    public void createOrUpdate(Question question) {
        if(question.getId()==null){
            //插入
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            questionMapper.insert(question);
        }else{
            // 更新
           Question updateQuestion=new Question();
           updateQuestion.setGmtModified(System.currentTimeMillis());
           updateQuestion.setTitle(question.getTitle());
           updateQuestion.setDescription(question.getDescription());
           updateQuestion.setTag(question.getTag());
           QuestionExample example=new QuestionExample();
           example.createCriteria()
                   .andIdEqualTo(question.getId());
            questionMapper.updateByExampleSelective(updateQuestion,example);
        }
    }
}
