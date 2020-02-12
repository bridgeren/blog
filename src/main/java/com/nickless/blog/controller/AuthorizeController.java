package com.nickless.blog.controller;

import com.nickless.blog.dto.AccessTokenDto;
import com.nickless.blog.dto.GithubUser;
import com.nickless.blog.mapper.UserMapper;
import com.nickless.blog.model.User;
import com.nickless.blog.provider.GitHubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;

import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * creat by nickless
 *
 * @Date 2019/12/31 8:59
 */
@Controller
public class AuthorizeController {

    @Autowired
    private GitHubProvider gitHubProvider;

    @Value("${github.client.id}")
    private  String clientId;

    @Value("${github.client.secret}")
    private  String clientSecret;

    @Value("${github.redirect.uri}")
    private  String redirectUri;

    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/callback")
    public String calllback(@RequestParam(name = "code") String code,
                            @RequestParam(name = "state") String state,

                            HttpServletResponse response) {
        AccessTokenDto accessTokenDto=new AccessTokenDto();

        accessTokenDto.setClient_id(clientId);
        accessTokenDto.setClient_secret(clientSecret);
        accessTokenDto.setCode(code);
        accessTokenDto.setRedirect_uri(redirectUri);
        accessTokenDto.setState(state);
        String accessToken= gitHubProvider.getAcessToken(accessTokenDto);
        GithubUser githubUser=gitHubProvider.getUser(accessToken);
        //System.out.println(githubUser.getName());
        if(githubUser!=null&&githubUser.getId()!=null){
            User user=new User();
            user.setToken(UUID.randomUUID().toString());
            user.setName(githubUser.getName());
            user.setAccountId(String.valueOf(githubUser.getId()));
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            user.setAvatarUrl(githubUser.getAvatarUrl());
            userMapper.insert(user);
            response.addCookie(new Cookie("token",user.getToken()));

           return  "redirect:/";
        }else {
            return "redirect:/";
        }
    }

}
