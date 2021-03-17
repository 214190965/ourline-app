package com.ourline.ourlineserve.service;

import com.ourline.ourlinecommon.code.BaseResult;

public interface ILineService {
    /**
     * 获取用户路径
     * */
    BaseResult getUserLine();

    /**
     * 获取节点路径映射
     * */
    BaseResult getPointLineMap(String pointid);
}
