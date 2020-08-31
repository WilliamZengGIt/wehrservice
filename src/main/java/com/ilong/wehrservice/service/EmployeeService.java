package com.ilong.wehrservice.service;

import com.ilong.wehrservice.mapper.EmployeeMapper;
import com.ilong.wehrservice.mapper.EmployeeecMapper;
import com.ilong.wehrservice.model.Employee;
import com.ilong.wehrservice.model.RespPageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.function.DoubleBinaryOperator;

/**
 * TOOD
 *
 * @author long
 * @date 2020-07-08 17:51
 */
@Service
public class EmployeeService {

    @Autowired
    EmployeeMapper employeeMapper;

    SimpleDateFormat yearFormat=new SimpleDateFormat("yyyy");
    SimpleDateFormat monthFormat=new SimpleDateFormat("MM");
    DecimalFormat decimalFormat=new DecimalFormat("##.00");


    public RespPageBean getEmployeeByPage(Integer page,Integer size,String keyword){
        if (page!=null&&size!=null){
            page=(page-1)*size;
        }
       List<Employee> data = employeeMapper.getEmployeeByPage(page, size,keyword);
        Long total = employeeMapper.getTotal(keyword);
        RespPageBean bean= new RespPageBean();
         bean.setData(data);
        bean.setTotal(total);
        return bean;
    }

    public Integer addEmp(Employee employee){
        Date beginContract = employee.getBeginContract();
        Date endContract = employee.getEndContract();
        double month =(Double.parseDouble(yearFormat.format(endContract))-Double.parseDouble(yearFormat.format(beginContract)))*12+
                (Double.parseDouble(monthFormat.format(endContract))-Double.parseDouble(monthFormat.format(beginContract)));

        employee.setContractTerm(Double.parseDouble(decimalFormat.format(month/12)));
        return employeeMapper.insertSelective(employee);
    }

    public Integer maxWorkID(){
        return employeeMapper.maxWorkID();
    }

    public Integer deleteEmpByEid(Integer id){
        return employeeMapper.deleteByPrimaryKey(id);
    }

    public Integer updateEmp(Employee employee){
        return employeeMapper.updateByPrimaryKeySelective(employee);
    }

    public int addEmps(List<Employee> list) {
        return employeeMapper.addEmps(list);
    }
}
