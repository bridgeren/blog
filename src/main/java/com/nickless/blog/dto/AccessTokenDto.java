package com.nickless.blog.dto;

import lombok.Data;

/**
 * creat by nickless
 *
 * @Date 2019/12/31 10:15
 */
@Data
public class AccessTokenDto {
    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;
    private String State;


}
