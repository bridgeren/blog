package com.nickless.blog.mapper;

import com.nickless.blog.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


/**
 * creat by nickless
 *
 * @Date 2020/1/2 13:21
 */
@Mapper
public interface UserMapper {

    @Insert("insert into user (account_id,name,token,gmt_creat,gmt_modified)values(#{accountId},#{name},#{token},#{gmtCreat},#{gmtModified})")
    void insert(User user);
    @Select("select * from user where token =#{token}")
    User findByToken(@Param("token") String token);
}
