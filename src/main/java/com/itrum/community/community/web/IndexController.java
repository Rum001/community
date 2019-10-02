package com.itrum.community.community.web;

import com.github.pagehelper.PageInfo;
import com.itrum.community.community.domain.Question;
import com.itrum.community.community.domain.User;
import com.itrum.community.community.dto.PagingDTO;
import com.itrum.community.community.service.QuestionService;
import com.itrum.community.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index(HttpServletRequest request, Model model, @RequestParam(name = "pageNum",defaultValue = "1")Integer pageNum,@RequestParam(name = "pageSize",defaultValue = "2")Integer pageSize) throws Exception{
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
        List<Question> questions= questionService.findAll(pageNum,pageSize);
        PageInfo<Question> pageInfo = new PageInfo<>(questions);
        PagingDTO pagingDTO = new PagingDTO(pageInfo.getPageNum(),pageInfo.getPageSize(),pageInfo.getTotal(),pageInfo.getPages());
        model.addAttribute("questionList",questions);
        model.addAttribute("pagingDTO",pagingDTO);
        return "index";
    }
}
