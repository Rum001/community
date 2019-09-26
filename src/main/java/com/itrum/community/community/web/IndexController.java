package com.itrum.community.community.web;

import com.itrum.community.community.domain.User;
import com.itrum.community.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index(HttpServletRequest request) throws Exception{
        //获取token 判断用户是否登录
        Cookie[] cookies = request.getCookies();
        if (cookies!=null){
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")){
                    String token = cookie.getValue();
                    User user=userService.findUserByToken(token);
                    if (user != null) {
                        request.getSession().setAttribute("user",user);
                        break;
                    }
                }
            }
        }

        return "index";
    }
}
