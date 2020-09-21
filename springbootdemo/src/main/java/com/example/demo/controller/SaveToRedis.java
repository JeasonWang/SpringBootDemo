package com.example.demo.controller;

import com.example.demo.service.SecKillService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: jeason
 * @Description:
 * @Date: 2020/9/20 20:55
 */
@RestController
@Api(description = "管理缓存")
public class SaveToRedis {
    @Autowired
    SecKillService secKillService;

    @GetMapping("/saveToRedis")
    public String saveToRedis(){
        return secKillService.saveToRedis();
    }

    @GetMapping("/clearRedis")
    public String clearRedis(){
        secKillService.clearRedis();
        return "clearRedis";
    }
}
