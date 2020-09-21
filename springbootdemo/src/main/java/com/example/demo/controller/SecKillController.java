package com.example.demo.controller;

import com.example.demo.service.SecKillService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: jeason
 * @Description:
 * @Date: 2020/9/20 0:17
 */
@RestController
@Api(description = "查看秒杀商品状态")
public class SecKillController {
    @Autowired
    SecKillService secKillService;

    @ApiOperation(value = "所有")
    @GetMapping("/seckill")
    public int countAllSeckill() {
        return secKillService.countAllSeckill();
    }

    @ApiOperation(value = "未开始")
    @GetMapping("/seckill/notstarted")
    public int countNotStartSeckill() {
        return secKillService.countNotStartSeckill();
    }

    @ApiOperation(value = "已开始")
    @GetMapping("/seckill/started")
    public int countStartedSeckill() {
        return secKillService.countStartedSeckill();
    }

}
