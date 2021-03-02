package com.ourline.ourlineserve.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository("userMapper")
public interface PointMapper {
    /**
     * 获取用户节点
     * */
    List<Map<String,Object>> getUserPoint(@Param("userId") String userId);
}
