package com.itrum.community.community.web;

import com.itrum.community.community.domain.Question;
import com.itrum.community.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("{id}")
    public String findQuestionById(@PathVariable("id")Integer id, Model model){
       Question question= questionService.findQuestionById(id);
       model.addAttribute("question",question);
       return "question";
    }
}
