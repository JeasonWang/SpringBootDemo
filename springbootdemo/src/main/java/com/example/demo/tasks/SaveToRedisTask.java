package com.example.demo.tasks;

import com.example.demo.service.SecKillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Author: jeason
 * @Description:
 * @Date: 2020/9/20 23:24
 */
@Component
public class SaveToRedisTask {
    @Autowired
    SecKillService secKillService;

    @Scheduled(cron = "0 30 * * * *")
    public void saveToRedis(){
        secKillService.saveToRedis();
    }

    @Scheduled(cron = "0 30 * * * *")
    public void clearRedis(){
        secKillService.clearRedis();
    }

}
