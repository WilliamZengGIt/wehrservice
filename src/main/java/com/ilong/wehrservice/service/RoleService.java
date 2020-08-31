package com.ilong.wehrservice.service;

import com.ilong.wehrservice.mapper.RoleMapper;
import com.ilong.wehrservice.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * TOOD
 *
 * @author long
 * @date 2020-06-17 15:14
 */
@Service
public class RoleService {

    @Autowired
    RoleMapper roleMapper;



    public List<Role> getAllRoles(){
        return roleMapper.getAllRoles();
    }

    public  Integer addRole(Role role){
        if (!role.getName().startsWith("ROLB_")){
            role.setName("ROLB_"+role.getName());
        }
        return roleMapper.insert(role);
    }

    public Integer deleteRoleById(Integer rid){
        return roleMapper.deleteByPrimaryKey(rid);
    }

}
