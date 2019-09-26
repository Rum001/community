package com.itrum.community.community.mapper;

import com.itrum.community.community.domain.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QuestionMapper {

    @Insert("insert into question(id,title,description,tag,creator,create_time) values(null,#{title},#{description},#{tag},#{creator},#{createTime})")
    void addQuestion(Question question);
}
