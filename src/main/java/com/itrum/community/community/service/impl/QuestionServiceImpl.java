package com.itrum.community.community.service.impl;

import com.github.pagehelper.PageHelper;
import com.itrum.community.community.domain.Question;
import com.itrum.community.community.domain.QuestionExample;
import com.itrum.community.community.domain.User;
import com.itrum.community.community.enums.ExceptionEnum;
import com.itrum.community.community.exception.CommunityException;
import com.itrum.community.community.mapper.QuestionMapper;
import com.itrum.community.community.mapper.UserMapper;
import com.itrum.community.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
       if (questionMapper.insertSelective(question)==0){
           throw new CommunityException(ExceptionEnum.);
       }
    }

    @Override
    public List<Question> findAll(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        //查询出所有的 question
        List<Question> questionList = questionMapper.selectByExample(new QuestionExample());
        for (Question question : questionList) {
            User user = userMapper.selectByPrimaryKey(Long.valueOf(question.getCreator()));
            question.setUser(user);
        }
        return questionList;
    }

    @Override
    public List<Question> findQuestionListByUserId(Long id, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        QuestionExample example = new QuestionExample();
        example.createCriteria().andCreatorEqualTo(Integer.valueOf(id.toString()));
        List<Question> questions = questionMapper.selectByExample(example);
        for (Question question : questions) {
            User user = userMapper.selectByPrimaryKey(Long.valueOf(question.getCreator()));
            question.setUser(user);
        }
        return questions;
    }

    @Override
    public Question findQuestionById(Long id) {
        Question question = questionMapper.selectByPrimaryKey(id);
        User user = userMapper.findUserById(question.getCreator());
        question.setUser(user);
        return question;
    }

    @Override
    public void editQuestion(Question question) {
        questionMapper.updateByPrimaryKeySelective(question);
    }
}
