package com.nickless.blog.model;

import lombok.Data;

/**
 * creat by nickless
 *
 * @Date 2020/1/6 15:59
 */
@Data
public class Question {
    private  Integer id;
    private  String title;
    private  String description;
    private  Long gmtCreate;
    private  Long gmtModified;
    private  Integer creator;
    private  Integer viewCount;
    private  Integer commentCount;
    private  Integer likeCount;
    private  String tag;


}
