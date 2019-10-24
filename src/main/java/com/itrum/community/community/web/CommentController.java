package com.itrum.community.community.web;

import com.alibaba.fastjson.JSON;
import com.itrum.community.community.domain.Comment;
import com.itrum.community.community.domain.User;
import com.itrum.community.community.dto.CommentsDTO;
import com.itrum.community.community.enums.ExceptionEnum;
import com.itrum.community.community.exception.CommunityException;
import com.itrum.community.community.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @ResponseBody
    @RequestMapping(value = "/comment",method = RequestMethod.POST)
    public Object comment(@RequestBody CommentsDTO commentDTO, HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        if (user==null){
            throw new CommunityException(ExceptionEnum.USER_NOT_LOGIN);
        }
        Comment comment = new Comment();
        comment.setCommentator(1L);
        comment.setContent(commentDTO.getContent());
        comment.setCreateTime(System.currentTimeMillis());
        comment.setParentId(commentDTO.getParentId());
        comment.setType(commentDTO.getType());
        comment.setLikeCount(0L);
        commentService.addComment(comment);
        Map<String,String>map=new HashMap<>();
        map.put("message","success");
        map.put("code","200");
        map.put("id",commentDTO.getParentId().toString());
        return JSON.toJSONString(map);
    }
}
