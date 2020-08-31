package com.ilong.wehrservice.controller.emp;

import com.ilong.wehrservice.model.*;
import com.ilong.wehrservice.service.*;
import com.ilong.wehrservice.utils.POIUtils;
import com.sun.org.apache.regexp.internal.REUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * TOOD
 *
 * @author long
 * @date 2020-07-08 17:46
 */
@RestController
@RequestMapping("/employee/basic")
public class EmpController {
    @Autowired
    EmployeeService employeeService;

    @Autowired
    NationService nationService;
    @Autowired
    PoliticsstatusService politicsstatusService;
    @Autowired
    JobLevelService jobLevelService;
    @Autowired
    DepartMentService departMentService;

    @Autowired
    PositionService positionService;

    @GetMapping("/")
    public RespPageBean  getEmployeeByPage(@RequestParam(defaultValue = "1") Integer page,
                                           @RequestParam(defaultValue = "10") Integer size,
                                           String  keyword
                                           ){

            return employeeService.getEmployeeByPage(page,size,keyword);
    }
    @PostMapping("/")
    public RespBean addEmp(@RequestBody Employee employee){
        if (employeeService.addEmp(employee)==1){
            return RespBean.ok("添加成功！");
        }
        return RespBean.error("添加失败！");
    }

    @GetMapping("/nations")
    public List<Nation> getAllNations(){
        return  nationService.getAllNations();
    }
    @GetMapping("/politicsstatus")
    public  List<Politicsstatus>  getAllPoliticsstatus(){
        return  politicsstatusService.getAllPoliticsstatus();
    }
    @GetMapping("/joblevels")
    public List<JobLevel> getAllJobLevels(){
        return jobLevelService.getAllJoblevels();
    }

    @GetMapping("/positions")
    public  List<Position> getAllPositions(){
        return positionService.getAllPositions();
    }

    @GetMapping("/maxWorkID")
    public RespBean maxWorkID(){
        final RespBean respBean = RespBean.build().setStatus(200)
                .setObj(String.format("%08d", employeeService.maxWorkID() + 1));
        return  respBean;
    }

    @GetMapping("/deps")
    public List<Department> getAllDepartments(){
        return departMentService.getAllDepartments();
    }
    @DeleteMapping("/{id}")
    public RespBean deleteEmpByEid(@PathVariable Integer id){
        if(employeeService.deleteEmpByEid(id)==1){
            return RespBean.ok("删除成功！");
        }
        return RespBean.error("删除失败！");

    }

    @PutMapping("/")
    public RespBean updateEmp(@RequestBody  Employee employee){
        if (employeeService.updateEmp(employee)==1) {
            return RespBean.ok("更新成功！");
        }

            return RespBean.error("更新失败！");
    }

    @GetMapping("/export")
    public ResponseEntity<byte[]> exportData(){
        List<Employee> list=(List<Employee>)
                employeeService.getEmployeeByPage(null,null,null).getData();
        return POIUtils.employee2Excel(list);
    }

    @PostMapping("/import")
    public RespBean importData(MultipartFile file) throws IOException {
/*            file.transferTo(new File("D:\\ilong.xls"));*/
        List<Employee> list=POIUtils.excel2Employee(file,nationService.getAllNations(),
                politicsstatusService.getAllPoliticsstatus(),
                departMentService.getAllDepartmentsWiehOutChildren(),
                positionService.getAllPositions(),
                jobLevelService.getAllJoblevels());
        if (employeeService.addEmps(list)==list.size()){

            return RespBean.ok("上传成功！");
        }

        return RespBean.error("上传失败！");
    }

}
