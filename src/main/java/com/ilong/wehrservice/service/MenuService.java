package com.ilong.wehrservice.service;

import com.ilong.wehrservice.mapper.MenuMapper;
import com.ilong.wehrservice.mapper.MenuRoleMapper;
import com.ilong.wehrservice.model.Hr;
import com.ilong.wehrservice.model.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.plugin.liveconnect.SecurityContextHelper;

import java.util.List;

/**
 * TOOD
 *
 * @author long
 * @date 2020-06-15 17:34
 */
@Service
public class MenuService {

    @Autowired
    MenuMapper menuMapper;
    
    @Autowired
    MenuRoleMapper menuRoleMapper;

    public List<Menu> getMenusByHrId(){
        return menuMapper.getMenusByHrId(
                ((Hr)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId());
    }

    public List<Menu> getAllMenus(){
        return menuMapper.getAllMenus();
    }

    public List<Integer> getMidsByRid(Integer rid){
        return menuMapper.getMidsByRid(rid);
    }

    @Transactional
    public boolean updateMunuRole(Integer rid,Integer[] mids){
        menuRoleMapper.deleteByRid(rid);
        Integer result = menuRoleMapper.insertRecord(rid, mids);
        return result==mids.length;

    }

}
