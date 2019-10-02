package com.itrum.community.community.service;

import com.itrum.community.community.domain.Question;

import java.util.List;

public interface QuestionService {

    void addQuestion(Question question);


    List<Question> findAll(Integer pageNum, Integer pageSize);

    List<Question> findQuestionListByUserId(Integer id, Integer pageNum, Integer pageSize);

    Question findQuestionById(Integer id);
}
