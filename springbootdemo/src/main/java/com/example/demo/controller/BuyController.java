package com.example.demo.controller;

import com.example.demo.service.BuyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: jeason
 * @Description:
 * @Date: 2020/9/20 0:17
 */
@RestController
@Api(description = "秒杀")
public class BuyController {
    @Autowired
    BuyService buyService;

    @ApiOperation(value = "普通秒杀")
    @PostMapping("/buy")
    public void buySecKill(String seckillId) throws Exception {
        buyService.buy(seckillId);
    }

    @ApiOperation(value = "限流秒杀")
    @PostMapping("/limitbuy")
    public void limitBuySecKill1(String seckillId) throws Exception {
        buyService.buy(seckillId);
    }

}
