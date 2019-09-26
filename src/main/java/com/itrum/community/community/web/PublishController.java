package com.itrum.community.community.web;

import com.itrum.community.community.domain.Question;
import com.itrum.community.community.domain.User;
import com.itrum.community.community.service.QuestionService;
import com.itrum.community.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/publish")
public class PublishController {
    @Autowired
    private QuestionService questionService;


    @GetMapping("/")
    public String publish(){
        return "publish";
    }

    @PostMapping("publishComment")
    public String publishComment(@RequestParam(name = "title",required = false)String title, @RequestParam(name = "description",required = false)String description,
                                 @RequestParam(name = "tag",required = false)String tag, HttpServletRequest request, Model model
                                 ){
        model.addAttribute("tag",tag);
        model.addAttribute("title",title);
        model.addAttribute("description",description);
        if (title==null||title==""){
            model.addAttribute("error","标题不能为空");
        }
        if (tag==null||tag==""){
            model.addAttribute("error","标签不能为空");
        }
        if (description==null||description==""){
            model.addAttribute("error","描述不能为空");
        }
        Question question = new Question();
        //从session中获取用户信息
        User user =(User) request.getSession().getAttribute("user");
        question.setCreator(user.getId());
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setCreateTime(String.valueOf(System.currentTimeMillis()));
        questionService.addQuestion(question);
        return "redirect:/";
    }
}
