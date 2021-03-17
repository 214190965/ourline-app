package com.ourline.ourlineserve.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository("pointMapper")
public interface PointMapper {
    /**
     * 获取用户节点
     * */
    List<Map<String,Object>> selectUserPoint(@Param("id") String id);

    /**
     * 保存用户节点
     * */
    Integer insertPoint(@Param("point") Map<String,Object> point);

    /**
     * 更新用户节点
     * */
    Integer updatePoint(@Param("point") Map<String,Object> point);
}
