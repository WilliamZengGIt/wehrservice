package com.ilong.wehrservice.service;

import com.ilong.wehrservice.mapper.PoliticsstatusMapper;
import com.ilong.wehrservice.model.Politicsstatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * TOOD
 *
 * @author long
 * @date 2020-07-11 17:02
 */
@Service
public class PoliticsstatusService {

    @Autowired
    PoliticsstatusMapper politicsstatusMapper;

    public List<Politicsstatus> getAllPoliticsstatus(){
        return politicsstatusMapper.getAllPoliticsstatus();
    }
}
