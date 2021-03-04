package com.ourline.ourlineserve.service.Impl;

import com.ourline.ourlinecommon.code.BaseResult;
import com.ourline.ourlineserve.service.IPointService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(rollbackFor = Exception.class, readOnly = true) // 发生异常事务回滚
@Service("pointService")
public class PointServiceImpl implements IPointService {

    /**
     * 通过所属对象获取节点
     *
     * @param owner
     */
    @Override
    public BaseResult getPointsByOwner(String owner) {
        return null;
    }
}
