package com.ilong.wehrservice.service;

import com.ilong.wehrservice.mapper.NationMapper;
import com.ilong.wehrservice.model.Nation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * TOOD
 *
 * @author long
 * @date 2020-07-11 17:01
 */
@Service
public class NationService {
    @Autowired
    NationMapper nationMapper;

    public List<Nation> getAllNations(){
        return nationMapper.getAllNations();
    }

}
