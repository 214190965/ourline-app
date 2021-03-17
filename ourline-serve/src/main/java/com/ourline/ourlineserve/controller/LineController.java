package com.ourline.ourlineserve.controller;


import com.ourline.ourlinecommon.code.BaseResult;
import com.ourline.ourlinecommon.util.MapperUtils;
import com.ourline.ourlineserve.service.ILineService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 路径管理
 * */
@RestController
@RequestMapping(value = "/serve")
public class LineController {

    @Resource
    private ILineService iLineService;
    /**
     * 获取用户路径
     * */
    @RequestMapping(value = "line",method = RequestMethod.GET)
    public String getUserPoint() throws Exception {
        BaseResult baseResult =  iLineService.getUserLine();
        return MapperUtils.obj2json(baseResult);
    }

    /**
     * 获取节点路径映射
     * */
    @RequestMapping(value = "plmap",method = RequestMethod.GET)
    public String getPoineLineMap(@RequestParam("pointid") String pointid) throws Exception {
        BaseResult baseResult =  iLineService.getPointLineMap(pointid);
        return MapperUtils.obj2json(baseResult);
    }
}
