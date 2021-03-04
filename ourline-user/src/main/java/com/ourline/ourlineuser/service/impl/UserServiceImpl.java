package com.ourline.ourlineuser.service.impl;


import com.ourline.ourlinecommon.code.BaseResult;
import com.ourline.ourlinecommon.code.StatusCode;
import com.ourline.ourlinecommon.util.SessionUtil;
import com.ourline.ourlineuser.mapper.UserMapper;
import com.ourline.ourlineuser.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Transactional(rollbackFor = Exception.class, readOnly = true) // 发生异常事务回滚
@Service("userService")
public class UserServiceImpl implements IUserService {

    @Resource(name = "userMapper")
    private UserMapper userMapper;

    @Resource(name = "sessionUtil")
    private SessionUtil sessionUtil;
    /**
     * 用户登录获取用户名和密码
     * @param name
     * @param pwd
     */
    @Override
    public BaseResult loginByNameAndPwd(String name, String pwd) {
        BaseResult baseResult = new BaseResult();
//        sessionUtil = new SessionUtil();
        Object result = userMapper.selectWithNameAndPwd(name,pwd);
        if(result != null){
            baseResult.setRetCode(StatusCode.CODE_SUCCESS.getCode());
        }else{
            baseResult.setRetMsg("用户名或密码错误");
        }
        System.out.println(sessionUtil.get("testKey").toString());
        return baseResult;
    }
}
