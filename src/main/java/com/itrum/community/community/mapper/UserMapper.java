package com.itrum.community.community.mapper;

import com.itrum.community.community.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import javax.jws.soap.SOAPBinding;

@Mapper
public interface UserMapper {

    @Insert("insert into user (id,account_id,name,token,create_time,update_time) values(null,#{accountId},#{name},#{token},#{createTime},#{updateTime})")
    void insert(User user) throws Exception;

    @Select("select id,account_id,name,token,create_time,update_time from user where account_id=#{accountId}")
    User findUserByAccountId(String accountId)  throws Exception;

    @Select("select id,account_id,name,token,create_time,update_time from user where token=#{token}")
    User findUserByToken(String token);
}
