package com.ilong.wehrservice.mapper;

import com.ilong.wehrservice.model.Hr;
import com.ilong.wehrservice.model.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HrMapper {

    Hr loadUserByUsername(String username);

    List<Hr> getAllHrs(@Param("hrid") Integer hrid,@Param("keywords") String keywords);

    Integer updateByPrimaryKeySelective(Hr hr);

    Integer deleteByPrimaryKey(Integer id);


}