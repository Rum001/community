package com.itrum.community.community.service.impl;

import com.github.pagehelper.PageHelper;
import com.itrum.community.community.domain.Question;
import com.itrum.community.community.domain.User;
import com.itrum.community.community.mapper.QuestionMapper;
import com.itrum.community.community.mapper.UserMapper;
import com.itrum.community.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public void addQuestion(Question question) {
        questionMapper.addQuestion(question);
    }

    @Override
    public List<Question> findAll(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        //查询出所有的 question
        List<Question> questionList = questionMapper.findAll();
        for (Question question : questionList) {
            User user = userMapper.findUserById(question.getCreator());
            question.setUser(user);
        }
        return questionList;
    }

    @Override
    public List<Question> findQuestionListByUserId(Integer id, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Question> questions = questionMapper.findQuestionListByUserId(id);
        for (Question question : questions) {
            User user = userMapper.findUserById(question.getCreator());
            question.setUser(user);
        }
        return questions;
    }

    @Override
    public Question findQuestionById(Integer id) {
        Question question = questionMapper.findQuestionById(id);
        User user = userMapper.findUserById(question.getCreator());
        question.setUser(user);
        return question;
    }
}
