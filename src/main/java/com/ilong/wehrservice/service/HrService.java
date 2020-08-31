package com.ilong.wehrservice.service;

import com.ilong.wehrservice.mapper.HrMapper;
import com.ilong.wehrservice.mapper.HrRoleMapper;
import com.ilong.wehrservice.model.Hr;
import com.ilong.wehrservice.utils.HrUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * TOOD
 *
 * @author long
 * @date 2020-06-14 17:09
 */
@Service
public class HrService implements UserDetailsService {

    @Autowired
    HrMapper hrMapper;

    @Autowired
    HrRoleMapper hrRoleMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Hr hr = hrMapper.loadUserByUsername(username);
        if (hr==null){
            throw new UsernameNotFoundException("用户名不存在");
        }
        return hr;
    }

    public List<Hr> getAllHrs(String keywords){

        return hrMapper.getAllHrs(HrUtils.getCurrentHr().getId(),keywords);
    }

    public Integer updateHr(Hr hr){
        return hrMapper.updateByPrimaryKeySelective(hr);
    }

    @Transactional
    public  boolean updateHrRole(Integer hrid,Integer[] rids){
        hrRoleMapper.deleteByHrid(hrid);
        return hrRoleMapper.addRole(hrid,rids)==rids.length;
    }

    public Integer deleteHrById(Integer id){
        return hrMapper.deleteByPrimaryKey(id);
    }

}
