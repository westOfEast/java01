package org.geekbang.wangzhi.week07.controller;

import com.alibaba.fastjson.JSON;
import org.geekbang.wangzhi.week07.config.DataSourceConfig;
import org.geekbang.wangzhi.week07.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private DataSourceConfig dataSourceConfig;

    @GetMapping("")
    public String getUser(@RequestParam("userId") Long userId){
        System.out.println(JSON.toJSON(dataSourceConfig));
        return JSON.toJSONString(userService.getUser(userId));
    }
}
