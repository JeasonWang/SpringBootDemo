package com.example.demo.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Author: jeason
 * @Description:
 * @Date: 2020/9/20 15:08
 */
@Repository
public interface SecKillMapper {
    @Select("select count(seckillId) from seckill")
    int countAllSeckill();

    @Select("select count(seckillId) from seckill where status = '1'")
    int countStartedSeckill();

    @Select("select count(seckillId) from seckill where status = '0'")
    int countNotStartSeckill();

    @Select("select count(seckillId) from seckill where status = '2'")
    int countEndSeckill();

    @Select("select * from seckill where status = '0' and start_time<=now() and end_time>=now()")
    List<Map<String,Object>> getNotStartedSecKil();

    @Select("select seckillId from seckill where status = '1' and end_time<=now()")
    List<String> getShouldEndSecKil();

    @Update("update seckill set status = #{status} where seckillId = #{seckillId}")
    int updateSecKillStatus(@Param("status") String status, @Param("seckillId") int seckillId);
}
