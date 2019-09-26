package com.itrum.community.community.service.impl;

import com.itrum.community.community.domain.Question;
import com.itrum.community.community.mapper.QuestionMapper;
import com.itrum.community.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionMapper questionMapper;
    @Override
    public void addQuestion(Question question) {
        questionMapper.addQuestion(question);
    }
}
