package com.itrum.community.community;

import com.itrum.community.community.domain.Question;
import com.itrum.community.community.domain.User;
import com.itrum.community.community.dto.CommentDTO;
import com.itrum.community.community.mapper.QuestionMapper;
import com.itrum.community.community.mapper.UserMapper;
import com.itrum.community.community.service.CommentService;
import com.itrum.community.community.service.QuestionService;
import com.itrum.community.community.service.UserService;
import lombok.AllArgsConstructor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommunityApplicationTests {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private UserService userService;

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private CommentService commentService;

    @Test
    public void contextLoads() {
        User all = null;
        try {
            all = userService.findUserByToken("441bd239-e910-44b6-aca9-74b68c1b5689");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(all.getId());
    }
    @Test
    public void addTest(){
        Question question = new Question();
        for (int i = 3; i < 33; i++) {
            question.setCreator(1);
            question.setTitle("标题"+i);
            question.setCreateTime(System.currentTimeMillis());
            question.setDescription("问题"+i);
            question.setTag("标签"+i);
            question.setViewCount(0L);
            question.setUpdateTime(null);
            question.setCommentCount(0L);
            question.setLikeCount(0L);
            questionMapper.insert(question);
        }
    }
    @Test
    public void idTest(){
        Question questionById = questionService.findQuestionById(1L);
        System.out.println(questionById);

    }
    @Test
    public void dToTest(){
        List<CommentDTO> commentByQuestionId = commentService.findCommentByQuestionId(1L,2);
        for (CommentDTO commentDTO : commentByQuestionId) {
            System.out.println(commentDTO);
        }

    }

}
