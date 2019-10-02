package com.itrum.community.community.mapper;

import com.itrum.community.community.domain.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface QuestionMapper {

    @Insert("insert into question(id,title,description,tag,creator,create_time) values(null,#{title},#{description},#{tag},#{creator},#{createTime})")
    void addQuestion(Question question);

    @Select("select id,title,description,tag,creator,create_time,comment_count,view_count,like_count from question")
    List<Question> findAll();

    @Select("select id,title,description,tag,creator,create_time,comment_count,view_count,like_count from question where creator=#{id}")
    List<Question> findQuestionListByUserId(Integer id);

    @Select("select id,title,description,tag,creator,create_time,comment_count,view_count,like_count from question where id=#{id}")
    Question findQuestionById(Integer id);
}
