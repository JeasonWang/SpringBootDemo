package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @Author: jeason
 * @Description:
 * @Date: 2020/9/21 0:12
 */
@Service
public class BuyService {
    @Autowired
    RedisTemplate redisTemplate;

    public void buy(String seckillId) throws InterruptedException {
        Thread.sleep(1000);
        Integer productId = (Integer)redisTemplate.opsForList().rightPop("seckill_product_"+seckillId);
        if (productId == null){
            throw new SecurityException("秒杀结束");
        }else {
            System.out.println("秒杀成功："+seckillId);
        }
    }

}
