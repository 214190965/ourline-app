package com.ourline.ourlineserve.service.Impl;

import com.ourline.ourlinecommon.code.BaseResult;
import com.ourline.ourlinecommon.code.StatusCode;
import com.ourline.ourlinecommon.entiy.User;
import com.ourline.ourlinecommon.util.DateUtil;
import com.ourline.ourlinecommon.util.IDGenerator;
import com.ourline.ourlinecommon.util.SessionUtil;
import com.ourline.ourlineserve.mapper.PointMapper;
import com.ourline.ourlineserve.service.IPointService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Transactional(rollbackFor = Exception.class, readOnly = true) // 发生异常事务回滚
@Service("pointService")
public class PointServiceImpl implements IPointService {

    @Resource(name = "pointMapper")
    private PointMapper pointMapper;

    @Resource(name = "sessionUtil")
    private SessionUtil sessionUtil;
    /**
     * 通过所属对象获取节点
     */
    @Override
    public BaseResult getPointsByOwner() {
        BaseResult baseResult = new BaseResult();
        //获取当前所属对象
        String owner = sessionUtil.getCurrentOwner();
        //返回数据
        List<Map<String,Object>> retMap = pointMapper.selectUserPoint(owner);
        baseResult.setRetData(retMap);
        baseResult.setRetCode(StatusCode.CODE_SUCCESS.getCode());
        return baseResult;
    }

    /**
     * 保存当前所属对象的节点
     *
     * @param point
     */
    @Override
    public BaseResult savePointWithOwner(Map<String,Object> point) {
        BaseResult baseResult = new BaseResult();
        //获取当前所属对象
        String owner = sessionUtil.getCurrentOwner();
        //获取当前用户
        User user = sessionUtil.getCurrentUser();
        String time = DateUtil.formatDateToString(new Date());
        //生成18位ID
        String id = IDGenerator.generator(18);
        point.put("id",id);
        point.put("owner",owner);
        point.put("user",user.getUserId());
        point.put("time",time);
        point.put("kind",1);
        point.put("size",1);
        point.put("color","#eeeeee");
        Integer retInt = pointMapper.insertPoint(point);
        if(retInt == 1){
            baseResult.setRetCode(StatusCode.CODE_SUCCESS.getCode());
        }
        return baseResult;
    }

    /**
     * 更新当前所属对象的节点
     *
     * @param point
     */
    @Override
    public BaseResult editPointWithOwner(Map<String, Object> point) {
        BaseResult baseResult = new BaseResult();
        //获取当前用户
        User user = sessionUtil.getCurrentUser();
        String time = DateUtil.formatDateToString(new Date());
        point.put("user",user.getUserId());
        point.put("time",time);
        //更新数据
        Integer retInt = pointMapper.updatePoint(point);
        if(retInt == 1){
            baseResult.setRetCode(StatusCode.CODE_SUCCESS.getCode());
        }
        return baseResult;
    }
}
