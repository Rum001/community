package com.itrum.community.community.web;

import com.itrum.community.community.domain.Question;
import com.itrum.community.community.enums.ExceptionEnum;
import com.itrum.community.community.exception.CommunityException;
import com.itrum.community.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("{id}")
    public String findQuestionById(@PathVariable("id")Integer id, Model model){
       Question question= questionService.findQuestionById(id);
       if (true)
       throw new CommunityException(ExceptionEnum.QUESTION_NOT_FOUND);
       model.addAttribute("question",question);
       return "question";
    }

    @GetMapping("/editQuestion/{id}")
    public String editQuestion(@PathVariable("id")Integer id,Model model){
        Question question = questionService.findQuestionById(id);
        model.addAttribute("title",question.getTitle());
        model.addAttribute("description",question.getDescription());
        model.addAttribute("tag",question.getTag());
        model.addAttribute("questionId",id);
        return "publish";
    }
}
