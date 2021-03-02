package com.ourline.ourlineuser.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository("userMapper")
public interface UserMapper {

    /**
     * 用户登录
     * */
    Object selectWithNameAndPwd(@Param("username") String username,@Param("password") String password);
}
