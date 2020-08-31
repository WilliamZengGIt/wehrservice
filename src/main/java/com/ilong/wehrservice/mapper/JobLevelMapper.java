package com.ilong.wehrservice.mapper;

import com.ilong.wehrservice.model.JobLevel;
import org.apache.ibatis.annotations.Param;


import java.util.List;

public interface JobLevelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(JobLevel record);

    int insertSelective(JobLevel record);

    JobLevel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(JobLevel record);

    int updateByPrimaryKey(JobLevel record);

    List<JobLevel> getAllJobLevels();

    Integer deleteJobLevelsByIds(@Param("ids") Integer[] ids);
}