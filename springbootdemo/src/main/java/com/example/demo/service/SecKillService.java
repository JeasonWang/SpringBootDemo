package com.example.demo.service;

import com.example.demo.mapper.SecKillMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @Author: jeason
 * @Description:
 * @Date: 2020/9/20 0:21
 */
@Service
@Slf4j
public class SecKillService {
    @Autowired
    SecKillMapper secKillMapper;
    @Autowired
    RedisTemplate redisTemplate;
    public int countAllSeckill(){
        return secKillMapper.countAllSeckill();
    }

    public int countEndSeckill(){
        return secKillMapper.countEndSeckill();
    }

    public int countNotStartSeckill(){
        return secKillMapper.countNotStartSeckill();
    }

    public int countStartedSeckill(){
        return secKillMapper.countStartedSeckill();
    }

    @Transactional
    public String saveToRedis(){
        // 查询出即将开始秒杀
        List<Map<String,Object>> list = secKillMapper.getNotStartedSecKil();
        for (Map<String, Object> tempmap : list) {
            //获取秒杀表主键
            Integer id = (Integer) tempmap.get("seckillId");
            //获取秒杀表商品id
            Integer productId = (Integer) tempmap.get("productId");
            //构建key
            String key = "seckill_product_" + id;
            //将num数量的秒杀名额对应的商品id存放到redis中
            Integer num=(Integer)tempmap.get("number");
            for(int i=0;i<num;i++){
                redisTemplate.opsForList().rightPush(key,productId);
            }
            //3.修改秒杀产品的状态
            secKillMapper.updateSecKillStatus("1",id);
        }
        return "存入redis"+list.size()+"个秒杀";
    }

    public void clearRedis(){
        List<String> list = secKillMapper.getShouldEndSecKil();
        for(String item : list){
            Integer seckillId = Integer.parseInt(item);
            secKillMapper.updateSecKillStatus("2",seckillId);
            redisTemplate.delete("seckill_product_" + seckillId);
        }
    }
}
