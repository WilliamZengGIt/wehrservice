package com.ilong.wehrservice.service;

import com.ilong.wehrservice.mapper.PositionMapper;
import com.ilong.wehrservice.model.Position;
import com.ilong.wehrservice.model.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * TOOD
 *
 * @author long
 * @date 2020-06-16 17:11
 */
@Service
public class PositionService {
    @Autowired
    PositionMapper positionMapper;

    public List<Position> getAllPositions(){
        return positionMapper.getAllPositions();
    }

    public Integer addPosition(Position position){
            position.setEnabled(true);
            position.setCreateDate(new Date());
            return positionMapper.insertSelective(position);
    }

    public Integer updatePositions(Position position){
            return positionMapper.updateByPrimaryKeySelective(position);
    }

    public Integer deletePositionById(Integer id){
        return positionMapper.deleteByPrimaryKey(id);
    }

    public Integer deletePositionsByids(Integer[] ids){
        return positionMapper.deletePositionsByIds(ids);
    }

}
