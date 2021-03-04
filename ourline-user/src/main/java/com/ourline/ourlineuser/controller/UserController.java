package com.ourline.ourlineuser.controller;

import com.ourline.ourlinecommon.code.BaseResult;
import com.ourline.ourlinecommon.util.MapperUtils;
import com.ourline.ourlineuser.service.IUserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 *用户管理
 * */
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Resource(name = "userService")
    private IUserService userService;

    /**
     * 用户登录
     * */
    @RequestMapping(value = "login",method = RequestMethod.POST)
    public String userLogin(@RequestParam("username") String username,
                            @RequestParam("password") String password) throws Exception {
        BaseResult baseResult =  userService.loginByNameAndPwd(username,password);
        return MapperUtils.obj2json(baseResult);
    }
}
