package com.itrum.community.community.mapper;

import com.itrum.community.community.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface UserMapper {

    @Insert("insert into user (id,account_id,name,token,create_time,update_time,avatar_url) values(null,#{accountId},#{name},#{token},#{createTime},#{updateTime},#{avatarUrl})")
    void insert(User user) throws Exception;

    @Select("select id,account_id,name,token,create_time,update_time,avatar_url from user where account_id=#{accountId}")
    User findUserByAccountId(String accountId)  throws Exception;

    @Select("select id,account_id,name,token,create_time,update_time,avatar_url from user where token=#{token}")
    User findUserByToken(String token);

    @Select("select id,account_id,name,token,create_time,update_time,avatar_url from user where id=#{id}")
    User findUserById(@Param("id") Integer creator);
}
