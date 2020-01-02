package com.nickless.blog.mapper;

import com.nickless.blog.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;


/**
 * creat by nickless
 *
 * @Date 2020/1/2 13:21
 */
@Mapper
public interface UserMapper {

    @Insert("insert into user (account_id,name,token,gmt_creat,gmt_modified)values(#{accountId},#{name},#{token},#{gmtCreat},#{gmtModified})")
    void insert(User user);
}
