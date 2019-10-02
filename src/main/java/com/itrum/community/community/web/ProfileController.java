package com.itrum.community.community.web;

import com.github.pagehelper.PageInfo;
import com.itrum.community.community.domain.Question;
import com.itrum.community.community.domain.User;
import com.itrum.community.community.dto.PagingDTO;
import com.itrum.community.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private QuestionService questionService;

    @RequestMapping("{action}")
    public String profile(@PathVariable("action")String action, Model model, HttpServletRequest request,
                          @RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,
                          @RequestParam(value = "pageSize",defaultValue = "5")Integer pageSize){
        User user = (User) request.getSession().getAttribute("user");
        if (user==null){
            model.addAttribute("error","请先登录");
            return "index";
        }
        if ("questions".equals(action)){
           List<Question>questionList= questionService.findQuestionListByUserId(user.getId(),pageNum,pageSize);
            PageInfo<Question> pageInfo = new PageInfo<>(questionList);
            PagingDTO pagingDTO = new PagingDTO(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getTotal(), pageInfo.getPages());
            model.addAttribute("pagingDTO",pagingDTO);
            model.addAttribute("questionList",questionList);
            model.addAttribute("action","questions");
            model.addAttribute("actionName","我的问题");
        }else {
            model.addAttribute("action","reply");
            model.addAttribute("actionName","最新回复");
        }
        return "profile";
    }
}
