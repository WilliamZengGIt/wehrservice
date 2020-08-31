package com.ilong.wehrservice.controller.config;

import com.ilong.wehrservice.model.Menu;
import com.ilong.wehrservice.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * TOOD
 *
 * @author long
 * @date 2020-06-15 17:24
 */
@RestController
@RequestMapping("/system/config")
public class SystemConfigController {

    @Autowired
    MenuService menuService;

    @GetMapping("/menu")
    public List<Menu> getMenusByHrid(){
        return menuService.getMenusByHrId();
    }
}
