package com.ourline.ourlineserve.controller;


import com.ourline.ourlinecommon.code.BaseResult;
import com.ourline.ourlinecommon.util.MapperUtils;
import com.ourline.ourlineserve.service.IPointService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 节点管理
 * */
@RestController
@RequestMapping(value = "/point")
public class PointController {

    @Resource
    private IPointService iPointService;

    /**
     * 获取节点
     * */
    @RequestMapping(value = "login",method = RequestMethod.POST)
    public String userLogin(@RequestParam("owner") String owner) throws Exception {
        BaseResult baseResult =  iPointService.getPointsByOwner(owner);
        return MapperUtils.obj2json(baseResult);
    }
}
