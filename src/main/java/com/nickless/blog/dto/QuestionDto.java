package com.nickless.blog.dto;

import com.nickless.blog.model.User;
import lombok.Data;

/**
 * creat by nickless
 *
 * @Date 2020/1/7 22:00
 */
@Data
public class QuestionDto {
    private Integer id;
    private String title;
    private String description;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer creator;
    private Integer viewCount;
    private Integer commentCount;
    private Integer likeCount;
    private String tag;
    private User user;
}
