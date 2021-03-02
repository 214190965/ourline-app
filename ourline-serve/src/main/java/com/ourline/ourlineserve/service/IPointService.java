package com.ourline.ourlineserve.service;

import com.ourline.framework.BaseResult;

public interface IPointService {

    /**
     * 通过所属对象获取节点
     * */
    BaseResult getPointsByOwner(String owner);
}
