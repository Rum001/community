package com.itrum.community.community.web;

import com.itrum.community.community.domain.Question;
import com.itrum.community.community.domain.User;
import com.itrum.community.community.service.QuestionService;
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

    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }

    @PostMapping("/publishComment")
    public String publishComment(@RequestParam(name = "title",required = false)String title, @RequestParam(name = "description",required = false)String description,
                                 @RequestParam(name = "tag",required = false)String tag, HttpServletRequest request, Model model,
                                 @RequestParam(name = "questionId",required = false)Long questionId
                                 ){
        model.addAttribute("tag",tag);
        model.addAttribute("title",title);
        model.addAttribute("description",description);
        if (title==null||title==""){
            model.addAttribute("error","标题不能为空");
            return "publish";
        }
        if (tag==null||tag==""){
            model.addAttribute("error","标签不能为空");
            return "publish";
        }
        if (description==null||description==""){
            model.addAttribute("error","描述不能为空");
            return "publish";
        }
        //如果 没有ID传过来就是增加 否则就是 修改
        if (questionId==null){
            Question question = new Question();
            //从session中获取用户信息
            User user =(User) request.getSession().getAttribute("user");
            question.setCreator(Integer.valueOf(user.getId().toString()));
            question.setTitle(title);
            question.setDescription(description);
            question.setTag(tag);
            question.setCreateTime(System.currentTimeMillis());
            questionService.addQuestion(question);
        }else {
            Question question = new Question();
            question.setId(questionId);
            question.setDescription(description);
            question.setTag(tag);
            question.setUpdateTime(System.currentTimeMillis());
            question.setTitle(title);
            questionService.editQuestion(question);
        }
        return "redirect:/";
    }
}
