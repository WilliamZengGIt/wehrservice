package com.ilong.wehrservice.controller.system.basic;

import com.ilong.wehrservice.model.Menu;
import com.ilong.wehrservice.model.RespBean;
import com.ilong.wehrservice.model.Role;
import com.ilong.wehrservice.service.MenuService;
import com.ilong.wehrservice.service.RoleService;
import com.sun.org.apache.regexp.internal.RE;
import org.omg.CORBA.INTERNAL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * TOOD
 *
 * @author long
 * @date 2020-06-17 15:13
 */
@RestController
@RequestMapping("/system/basic/permiss")
public class PermissController {

    @Autowired
    RoleService roleService;


    @Autowired
    MenuService menuService;

    @GetMapping("/")
    public List<Role> getAllRoles(){
        return roleService.getAllRoles();
    }

    @GetMapping("/menus")
    public List<Menu> getAllMenus(){
        return menuService.getAllMenus();
    }

    @GetMapping("/mids/{rid}")
    public List<Integer> getMidsByRid(
            @PathVariable Integer rid){
        return menuService.getMidsByRid(rid);
    }

    @PutMapping("/")
    public RespBean updateMunuRole(Integer rid,Integer[] mids){
        if (menuService.updateMunuRole(rid,mids)){
            return RespBean.ok("更新成功！");
        }
        return  RespBean.error("更新失败！");
    }

    @PostMapping("/role")
    public RespBean addRole(@RequestBody Role role){
            if (roleService.addRole(role)==1){
                return RespBean.ok("添加成功！");
            }
            return RespBean.error("添加失败！");
    }

    @DeleteMapping("/role/{rid}")
    public  RespBean deleteRoleById(@PathVariable Integer rid){
        if (roleService.deleteRoleById(rid)==1){
            return RespBean.ok("删除成功！");
        }
        return RespBean.error("删除失败！");
    }
}
