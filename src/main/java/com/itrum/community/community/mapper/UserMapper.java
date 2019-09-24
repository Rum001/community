package com.itrum.community.community.mapper;

import com.itrum.community.community.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    @Insert("insert into user (id,account_id,name,token,gmt_create,gmt_modified) values(null,#{accountId},#{name},#{token},#{gmtCreate},#{gmtModified})")
    void insert(User user) throws Exception;

}
