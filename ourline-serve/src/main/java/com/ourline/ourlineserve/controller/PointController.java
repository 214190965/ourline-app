package com.ourline.ourlineserve.controller;


import com.ourline.ourlinecommon.code.BaseResult;
import com.ourline.ourlinecommon.util.MapperUtils;
import com.ourline.ourlineserve.service.IPointService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 节点管理
 * */
@RestController
@RequestMapping(value = "/serve")
public class PointController {

    @Resource
    private IPointService iPointService;

    /**
     * 获取用户节点
     * */
    @RequestMapping(value = "point",method = RequestMethod.GET)
    public String getUserPoint() throws Exception {
        BaseResult baseResult =  iPointService.getPointsByOwner();
        return MapperUtils.obj2json(baseResult);
    }

    /**
     * 保存用户节点
     * */
    @RequestMapping(value = "save",method = RequestMethod.POST)
    public String savePoint(@RequestBody Map<String,Object> point) throws Exception {
        BaseResult baseResult =  iPointService.savePointWithOwner(point);
        return MapperUtils.obj2json(baseResult);
    }

    /**
     * 更新节点
     * */
    @RequestMapping(value = "edit",method = RequestMethod.POST)
    public String editPoint(@RequestBody Map<String,Object> point) throws Exception {
        BaseResult baseResult =  iPointService.editPointWithOwner(point);
        return MapperUtils.obj2json(baseResult);
    }

}
