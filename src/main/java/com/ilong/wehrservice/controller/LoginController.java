package com.ilong.wehrservice.controller;

import com.ilong.wehrservice.model.RespBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TOOD
 *
 * @author long
 * @date 2020-06-14 18:22
 */
@RestController
public class LoginController {

    @GetMapping("/login")
    public RespBean login(){
        return RespBean.error("尚未登录，请登录");
    }

}
