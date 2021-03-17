package com.ourline.ourlineserve.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository("lineMapper")
public interface LineMapper {

    /**
     * 获取用户路径
     * */
    List<Map<String,Object>> getUserLine(@Param("id") String id);

    /**
     * 获取节点路径映射
     * */
    List<Map<String,Object>> getPointLineMap(@Param("id") String id);

}
