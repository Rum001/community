package com.itrum.community.community;

import com.github.pagehelper.PageInfo;
import com.itrum.community.community.domain.Question;
import com.itrum.community.community.service.QuestionService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommunityApplicationTests {

    @Autowired
    private QuestionService questionService;

    @Test
    public void contextLoads() {
        List<Question> all = questionService.findAll(1,4);
        PageInfo<Question> questionPageInfo = new PageInfo<>(all);
        for (Question questionDTO : all) {
            System.out.println(questionDTO);
        }
    }

    @Test
    public void test2(){
        List<Question> questionListByUserId = questionService.findQuestionListByUserId(2, 1, 2);
        for (Question question : questionListByUserId) {
            System.out.println(question);
        }
    }
    @Test
    public void test3(){
        Question question = questionService.findQuestionById(2);
        System.out.println(question);
    }


    //    @Test
//    public void test() {
//        String str="Hello";
//        FileWriter fs=null;
//        try {
//            fs=new FileWriter("c:\\hello.txt");
//            fs.write(str);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }finally {
//
//        }
//    }
    @Test
    public void test(){
        Map<String,String> map =new HashMap<>();
        map.put("dog1","123456");
        map.put("dog1","123");
        map.put("dog32","1234");
        map.put("dog2","12345");

        System.out.println(map.get("dog1"));
//        Set<String> strings = map.keySet();
//        for (String string : strings) {
//            System.out.println(string);
//
//        }

    }

}
