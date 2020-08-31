package com.ilong.wehrservice.service;

import com.ilong.wehrservice.mapper.JobLevelMapper;
import com.ilong.wehrservice.model.JobLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * TOOD
 *
 * @author long
 * @date 2020-06-17 10:48
 */
@Service
public class JobLevelService {

    @Autowired
    JobLevelMapper jobLevelMapper;

    public List<JobLevel> getAllJoblevels(){
        return jobLevelMapper.getAllJobLevels();
    }

    public Integer addJoblevels(JobLevel jobLevel){
        jobLevel.setCreateDate(new Date());
        jobLevel.setEnabled(true);
        return jobLevelMapper.insertSelective(jobLevel);
    }

    public Integer updateJobleveById(JobLevel jobLevel){
        return jobLevelMapper.updateByPrimaryKeySelective(jobLevel);
    }

    public  Integer deleteJoblevelById(Integer id){
        return jobLevelMapper.deleteByPrimaryKey(id);
    }

    public Integer deleeteJobLevelByids(Integer[] ids){
        return jobLevelMapper.deleteJobLevelsByIds(ids);
    }


}
