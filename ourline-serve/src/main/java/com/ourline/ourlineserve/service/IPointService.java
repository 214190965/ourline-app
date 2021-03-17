package com.ourline.ourlineserve.service;


import com.ourline.ourlinecommon.code.BaseResult;

import java.util.Map;

public interface IPointService {

    /**
     * 通过所属对象获取节点
     * */
    BaseResult getPointsByOwner();

    /**
     * 保存当前所属对象的节点
     * */
    BaseResult savePointWithOwner(Map<String,Object> point);

    /**
     * 更新当前所属对象的节点
     * */
    BaseResult editPointWithOwner(Map<String,Object> point);
}
