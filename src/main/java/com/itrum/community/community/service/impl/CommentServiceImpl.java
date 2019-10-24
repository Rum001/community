package com.itrum.community.community.service.impl;

import com.itrum.community.community.domain.*;
import com.itrum.community.community.dto.CommentDTO;
import com.itrum.community.community.enums.CommunityTypeEnum;
import com.itrum.community.community.enums.ExceptionEnum;
import com.itrum.community.community.exception.CommunityException;
import com.itrum.community.community.mapper.CommentMapper;
import com.itrum.community.community.mapper.QuestionMapper;
import com.itrum.community.community.mapper.UserMapper;
import com.itrum.community.community.service.CommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public void addComment(Comment comment) {
        if (comment.getParentId()==null||comment.getParentId()==0){
            throw new CommunityException(ExceptionEnum.COMMENT_NOT_FOUND);
        }
        if (comment.getType()==null|| !CommunityTypeEnum.isExists(comment.getType())){
            throw new CommunityException(ExceptionEnum.COMMENT_NOT_FOUND);
        }
        if (comment.getType()==CommunityTypeEnum.COMMENT.getType()){
            //添加回复评论
            CommentExample commentExample = new CommentExample();
            CommentExample.Criteria criteria = commentExample.createCriteria();
            criteria.andParentIdEqualTo(comment.getParentId());
            List<Comment> dbComments = commentMapper.selectByExample(commentExample);
            if (dbComments.size() == 0) {
                throw new CommunityException(ExceptionEnum.COMMENT_NOT_FOUND);
            }
            int count = commentMapper.insertSelective(comment);
            if (count!=1){
                throw new CommunityException(ExceptionEnum.COMMENT_SAVE_FAILING);
            }
        }else {
            //添加回复问题
            Question question = questionMapper.selectByPrimaryKey(comment.getParentId());
            if (question == null) {
                throw new CommunityException(ExceptionEnum.QUESTION_NOT_FOUND);
            }
            int count = commentMapper.insertSelective(comment);
            if (count!=1){
                throw new CommunityException(ExceptionEnum.COMMENT_SAVE_FAILING);
            }
            //修改回复数
            synchronized (this){
                Question record = new Question();
                record.setId(question.getId());
                record.setCommentCount(question.getCommentCount()+1);
                questionMapper.updateByPrimaryKeySelective(record);
            }
        }
    }
    @Override
    public List<CommentDTO> findCommentByQuestionId(Long id,Integer type) {
        //查询出该问题下所有的评论并且是评论的是问题
        CommentExample example = new CommentExample();
        example.createCriteria().andParentIdEqualTo(id).andTypeEqualTo(type);
        example.setOrderByClause("create_time desc");
        List<Comment> comments = commentMapper.selectByExample(example);
        //获取评论里的所有的评论人的ID 并且去除重复的评论
        Set<Long> collectSet = comments.stream().map(comment -> comment.getCommentator()).collect(Collectors.toSet());
        //有了评论人的ID就可以查询出该评论人的信息
        UserExample userExample = new UserExample();
        userExample.createCriteria().andIdIn(new ArrayList<>(collectSet));
        //所有该问题下评论人的信息
        List<User> users = userMapper.selectByExample(userExample);
        //将他转为一个Map更好的放入CommentDTO中
        Map<Long, User> userMap = users.stream().collect(Collectors.toMap(user -> user.getId(), user -> user));
        List<CommentDTO> commentDTOS = comments.stream().map(comment -> {
            CommentDTO commentDTO = new CommentDTO();
            BeanUtils.copyProperties(comment,commentDTO);
            commentDTO.setUser(userMap.get(comment.getCommentator()));
            return commentDTO;
        }).collect(Collectors.toList());
        return commentDTOS;
    }
}
