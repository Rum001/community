package com.itrum.community.community.web;

import com.itrum.community.community.domain.User;
import com.itrum.community.community.dto.AccessTokenDTO;
import com.itrum.community.community.dto.GithubUserDTO;
import com.itrum.community.community.provider.GithubProvider;
import com.itrum.community.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
@EnableConfigurationProperties(AccessTokenDTO.class)
public class AuthorizationController {

    @Autowired
    private GithubProvider githubProvider;

    @Autowired
    private UserService userService;

    @Autowired
    private AccessTokenDTO accessTokenDTO;

//    @Value("cm.githubUser.client_id")
//    private String client_id;
//    @Value("cm.githubUser.client_secret")
//    private String client_secret;
//    @Value("cm.githubUser.redirect_uri")
//    private String redirect_uri;
//    @Value("cm.githubUser.state")
//    private String state;

    @GetMapping("callback")
    public String callback(@RequestParam(name = "code")String code, HttpServletResponse response) throws Exception {
        accessTokenDTO.setCode(code);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUserDTO githubUser = githubProvider.getUser(accessToken);
        if (githubUser!=null){
            //登录成功 生成token
            String token = UUID.randomUUID().toString();
            //判断该用户是否在数据库中存在
            User accountUser = userService.findUserByAccountId(githubUser.getId().toString());
            if (accountUser==null) {
                //不存在 将用户存入数据库中
                User user = new User();
                user.setAccountId(String.valueOf(githubUser.getId()));
                user.setCreateTime(String.valueOf(System.currentTimeMillis()));
                user.setToken(token);
                user.setAvatarUrl(githubUser.getAvatar_url());
                user.setName(githubUser.getName());
                userService.insert(user);
            }else {
                 token = accountUser.getToken();
            }
            response.addCookie(new Cookie("token",token));
            return "redirect:/";
        }
        return "redirect:/";
    }



}
