package com.itrum.community.community.web;

import com.itrum.community.community.domain.Question;
import com.itrum.community.community.dto.CommentDTO;
import com.itrum.community.community.enums.CommunityTypeEnum;
import com.itrum.community.community.enums.ExceptionEnum;
import com.itrum.community.community.exception.CommunityException;
import com.itrum.community.community.service.CommentService;
import com.itrum.community.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private CommentService commentService;

    @GetMapping("{id}")
    public String findQuestionById(@PathVariable("id") Long id, Model model) {
        Question question = questionService.findQuestionById(id);
        if (question == null) {
            throw new CommunityException(ExceptionEnum.QUESTION_NOT_FOUND);
        }
        //增加阅读数
        questionService.addViewCount(question);
        //查询该问题下的所有的评论
        List<CommentDTO> comments = commentService.findCommentByQuestionId(id, CommunityTypeEnum.QUESTION.getType());
        //查询该问题下所有二级评论
        List<CommentDTO> secondComments = commentService.findCommentByQuestionId(id, CommunityTypeEnum.COMMENT.getType());
        if (question != null) {
            model.addAttribute("question", question);
        }
        model.addAttribute("comments", comments);
        model.addAttribute("secondComments", secondComments);
        return "question";
    }

    @GetMapping("/editQuestion/{id}")
    public String editQuestion(@PathVariable("id") Integer id, Model model) {
        Question question = questionService.findQuestionById(Long.valueOf(id));
        model.addAttribute("title", question.getTitle());
        model.addAttribute("description", question.getDescription());
        model.addAttribute("tag", question.getTag());
        model.addAttribute("questionId", id);
        return "publish";
    }
}
