package com.taotao.service.impl;

import com.taotao.commons.TaotaoResult;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;


@Service

public class ItemServiceImpl implements ItemService {
    @Autowired
    private TbItemMapper tbItemMapper;
   /* @Resource
    private RedisTemplate<Object,Object> redisTemplate;*/

    @Override
    public TaotaoResult getItemDetails(Long id) {
       /* //查询缓存
        TbItem tbItem =(TbItem) redisTemplate.opsForValue().get("id");
        *//*TaotaoResult result = (TaotaoResult)redisTemplate.opsForValue().get("id");*//*

        if (tbItem == null) {

               tbItem = tbItemMapper.selectByPrimaryKey(id);
                redisTemplate.opsForValue().set("id",tbItem,60, TimeUnit.SECONDS);
            System.out.println("*************");


        }
        return TaotaoResult.ok(tbItem);
    }*/

        try {
            TbItem tbItem = tbItemMapper.selectByPrimaryKey(id);
            return TaotaoResult.ok(tbItem);
        } catch (Exception e) {
            return TaotaoResult.error("查询商品详情出错");
        }
    }

}
