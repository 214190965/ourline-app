package com.ourline.ourlineserve.service.Impl;

import com.ourline.ourlinecommon.code.BaseResult;
import com.ourline.ourlinecommon.code.StatusCode;
import com.ourline.ourlinecommon.entiy.User;
import com.ourline.ourlinecommon.util.SessionUtil;
import com.ourline.ourlineserve.mapper.LineMapper;
import com.ourline.ourlineserve.service.ILineService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Transactional(rollbackFor = Exception.class, readOnly = true) // 发生异常事务回滚
@Service("lineService")
public class LineServiceImpl implements ILineService {

    @Resource(name = "lineMapper")
    private LineMapper lineMapper;

    @Resource(name = "sessionUtil")
    private SessionUtil sessionUtil;
    /**
     * 获取用户路径
     */
    @Override
    public BaseResult getUserLine() {
        BaseResult baseResult = new BaseResult();
        //获取当前用户
        User user = sessionUtil.getCurrentUser();
        List<Map<String,Object>> retMap = lineMapper.getUserLine(user.getUserId());
        baseResult.setRetData(retMap);
        baseResult.setRetCode(StatusCode.CODE_SUCCESS.getCode());
        return baseResult;
    }

    /**
     * 获取节点路径映射
     *
     * @param pointid
     */
    @Override
    public BaseResult getPointLineMap(String pointid) {
        BaseResult baseResult = new BaseResult();
        //获取当前用户
        //User user = sessionUtil.getCurrentUser();
        List<Map<String,Object>> retMap = lineMapper.getPointLineMap(pointid);
        baseResult.setRetData(retMap);
        baseResult.setRetCode(StatusCode.CODE_SUCCESS.getCode());
        return baseResult;
    }
}
