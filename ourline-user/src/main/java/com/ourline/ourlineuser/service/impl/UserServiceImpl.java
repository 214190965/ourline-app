package com.ourline.ourlineuser.service.impl;


import com.ourline.ourlinecommon.code.BaseResult;
import com.ourline.ourlinecommon.code.StatusCode;
import com.ourline.ourlinecommon.code.WebConstants;
import com.ourline.ourlinecommon.entiy.User;
import com.ourline.ourlinecommon.redis.service.IRedisService;
import com.ourline.ourlinecommon.util.SessionUtil;
import com.ourline.ourlinecommon.util.UUIDGenerator;
import com.ourline.ourlineuser.mapper.UserMapper;
import com.ourline.ourlineuser.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Transactional(rollbackFor = Exception.class, readOnly = true) // 发生异常事务回滚
@Service("userService")
public class UserServiceImpl implements IUserService {

    @Resource(name = "userMapper")
    private UserMapper userMapper;

    @Resource(name = "sessionUtil")
    private SessionUtil sessionUtil;

    @Resource(name = "redisService")
    private IRedisService redisService;

    /**
     * 用户登录获取用户名和密码
     * @param id
     * @param pwd
     */
    @Override
    public BaseResult loginByNameAndPwd(String id, String pwd) {
        BaseResult baseResult = new BaseResult();
        Map<String,Object> result = userMapper.selectWithNameAndPwd(id,pwd);
        //存在用户
        if(result != null){
            baseResult.setRetCode(StatusCode.CODE_SUCCESS.getCode());
            //生成sessionId(token)
            String sessionId = UUIDGenerator.uuid32();
            Map<String,Object> retMap = new HashMap<>();
            retMap.put("token",sessionId);
            retMap.put("username",result.get("username"));
            baseResult.setRetData(retMap);
            //把user信息放入redis,
            User user = new User();
            user.setUserId(id);
            user.setUserName(result.get("username").toString());
            user.setUserPwd(pwd);
            HashMap<String,Object> sessionMap = new HashMap<>();
            sessionMap.put(WebConstants.SESSION_USER,user);
            // 把用户信息存到redis,有效期30分钟
            sessionUtil.putMap(sessionId, sessionMap, 1800L);
        }else{
            baseResult.setRetMsg("用户名或密码错误");
        }
        return baseResult;
    }
}
