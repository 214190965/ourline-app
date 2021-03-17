package com.ourline.ourlineuser.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository("userMapper")
public interface UserMapper {

    /**
     * 用户登录
     * */
    Map<String,Object> selectWithNameAndPwd(@Param("userid") String username, @Param("password") String password);


}
