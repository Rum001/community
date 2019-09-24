package com.itrum.community.community.web;

import com.itrum.community.community.domain.User;
import com.itrum.community.community.dto.AccessTokenDTO;
import com.itrum.community.community.dto.GithubUserDTO;
import com.itrum.community.community.provider.GithubProvider;
import com.itrum.community.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Controller
public class AuthorizationController {

    @Autowired
    private GithubProvider githubProvider;

    @Autowired
    private UserService userService;

    @GetMapping("callback")
    public String callback(@RequestParam(name = "code")String code, HttpServletRequest request) throws Exception {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id("91c6cbec3712a2f3de54");
        accessTokenDTO.setClient_secret("e9243f6d1d140f5a951fbefaaa4192cc1adc035d");
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri("http://localhost:8887/callback");
        accessTokenDTO.setState("1");
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUserDTO githubUser = githubProvider.getUser(accessToken);
        if (githubUser!=null){
            //登录成功 将用户信息放入 session中 存入数据库中
            User user = new User();
            user.setAccountId(String.valueOf(githubUser.getId()));
            user.setGmtCreate(String.valueOf(System.currentTimeMillis()));
            user.setToken(UUID.randomUUID().toString());
            user.setName(githubUser.getName());
            userService.insert(user);
            request.getSession().setAttribute("githubUser",githubUser);
            return "redirect:/";
        }
        return "redirect:/";
    }



}
