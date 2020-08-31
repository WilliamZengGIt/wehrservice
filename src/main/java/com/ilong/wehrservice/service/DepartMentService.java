package com.ilong.wehrservice.service;

import com.ilong.wehrservice.mapper.DepartmentMapper;
import com.ilong.wehrservice.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;

/**
 * TOOD
 *
 * @author long
 * @date 2020-06-17 17:48
 */
@Service
public class DepartMentService {

    @Autowired
    DepartmentMapper departmentMapper;

    public List<Department> getAllDepartments(){
        return  departmentMapper.getAllDepartmentsByParentId(-1);
    }

    public void  addDep(Department dep){
        dep.setEnabled(true);
        departmentMapper.addDep(dep);
    }

    public void deleteDepById(Department dep){
            departmentMapper.deleteDepById(dep);
    }

    public List<Department> getAllDepartmentsWiehOutChildren() {
        return departmentMapper.getAllDepartmentsWithOutChildren();
    }
}
