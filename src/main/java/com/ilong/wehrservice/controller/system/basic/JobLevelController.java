package com.ilong.wehrservice.controller.system.basic;

import com.ilong.wehrservice.model.JobLevel;
import com.ilong.wehrservice.model.RespBean;
import com.ilong.wehrservice.service.JobLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * TOOD
 *
 * @author long
 * @date 2020-06-17 10:46
 */
@RestController
@RequestMapping("/system/basic/joblevel")
public class JobLevelController {

    @Autowired
    public JobLevelService jobLevelService;

    @GetMapping("/")
    public List<JobLevel> getAllJoblevels(){
        return jobLevelService.getAllJoblevels();

        }
    @PostMapping("/")
    public RespBean addJoblevels(@RequestBody JobLevel jobLevel){
        if (jobLevelService.addJoblevels(jobLevel)==1){
            return RespBean.ok("添加成功！");
        }
        return RespBean.error("添加失败!");
    }

    @PutMapping("/")
    public RespBean updateJobleveById(@RequestBody JobLevel jobLevel){
        if(jobLevelService.updateJobleveById(jobLevel)==1){
            return RespBean.ok("更新成功！");
        }
        return RespBean.error("更新失败!");
    }

    @DeleteMapping("/{id}")
    public  RespBean deleteJoblevelById(@PathVariable Integer id){
        if (jobLevelService.deleteJoblevelById(id)==1){
            return RespBean.ok("删除成功！");
        }
        return RespBean.error("删除失败!");
    }

    @DeleteMapping("/")
    public RespBean deleeteJobLevelByids(Integer[] ids){
            if (jobLevelService.deleeteJobLevelByids(ids)==ids.length) {
                return RespBean.ok("删除成功！");
            }
            return  RespBean.error("删除失败！");
            }
    }

