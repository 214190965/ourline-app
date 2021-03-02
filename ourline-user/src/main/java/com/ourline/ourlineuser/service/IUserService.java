package com.ourline.ourlineuser.service;

import com.ourline.framework.BaseResult;


public interface IUserService {
    /**
     * 获取用户名和密码
     * */
    BaseResult loginByNameAndPwd(String name, String pwd);
}
