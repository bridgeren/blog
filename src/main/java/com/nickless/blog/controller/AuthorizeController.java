package com.nickless.blog.controller;

import com.nickless.blog.dto.AccessTokenDto;
import com.nickless.blog.dto.GithubUser;
import com.nickless.blog.provider.GitHubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

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

    @RequestMapping("/callback")
    public String calllback(@RequestParam(name = "code") String code,
                            @RequestParam(name = "state") String state,
                            HttpServletRequest request) {
        AccessTokenDto accessTokenDto=new AccessTokenDto();

        accessTokenDto.setClient_id(clientId);
        accessTokenDto.setClient_secret(clientSecret);
        accessTokenDto.setCode(code);
        accessTokenDto.setRedirect_uri(redirectUri);
        accessTokenDto.setState(state);
        String accessToken= gitHubProvider.getAcessToken(accessTokenDto);
        GithubUser user=gitHubProvider.getUser(accessToken);
        System.out.println(user.getName());
        if(user!=null){
           request.getSession().setAttribute("user",user);
           return  "redirect:/";
        }else {
            return "redirect:/";
        }
    }

}
