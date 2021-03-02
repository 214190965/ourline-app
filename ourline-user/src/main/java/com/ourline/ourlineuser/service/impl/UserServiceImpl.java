package com.ourline.ourlineuser.service.impl;

import com.ourline.framework.BaseResult;
import com.ourline.framework.StatusCode;
import com.ourline.ourlineuser.mapper.UserMapper;
import com.ourline.ourlineuser.service.IUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Transactional(rollbackFor = Exception.class, readOnly = true) // 发生异常事务回滚
@Service("userService")
public class UserServiceImpl implements IUserService {

    @Resource(name = "userMapper")
    private UserMapper userMapper;
    /**
     * 用户登录获取用户名和密码
     * @param name
     * @param pwd
     */
    @Override
    public BaseResult loginByNameAndPwd(String name, String pwd) {
        BaseResult baseResult = new BaseResult();
        Object result = userMapper.selectWithNameAndPwd(name,pwd);
        if(result != null){
            baseResult.setRetCode(StatusCode.CODE_SUCCESS.getCode());
        }else{
            baseResult.setRetMsg("用户名或密码错误");
        }
        return baseResult;
    }
}
