package com.ilong.wehrservice.controller.system.basic;

import com.ilong.wehrservice.model.Department;
import com.ilong.wehrservice.model.RespBean;
import com.ilong.wehrservice.service.DepartMentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * TOOD
 *
 * @author long
 * @date 2020-06-17 17:43
 */
@RestController
@RequestMapping("/system/basic/department")
public class DepartmentController {

    @Autowired
    DepartMentService departMentService;

    @GetMapping("/")
    public List<Department> getAllDepartments(){
        return departMentService.getAllDepartments();
    }


    @PostMapping("/")
    public RespBean addDep(@RequestBody Department dep){

        departMentService.addDep(dep);

        if (dep.getResult()==1){
        return RespBean.ok("添加成功",dep);
        }
        return RespBean.error("添加失败");

    }

    @DeleteMapping("/{id}")
    public RespBean deleteDepById(@PathVariable  Integer id){

        Department dep = new Department();
        dep.setId(id);
        departMentService.deleteDepById(dep);
        if(dep.getResult()==-2){
            return RespBean.error("该部门下有子部门，删除失败!");
        }else if (dep.getResult()==-1){
            return RespBean.error("该部门下有员工，删除失败！");
        }else if (dep.getResult()==1){
            return RespBean.ok("删除成功！");
        }
        return RespBean.error("删除失败");

    }



}
