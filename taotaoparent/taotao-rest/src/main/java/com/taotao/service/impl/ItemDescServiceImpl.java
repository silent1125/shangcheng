package com.taotao.service.impl;

import com.taotao.commons.TaotaoResult;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.pojo.TbItemDesc;
import com.taotao.service.ItemDescService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Service
public class ItemDescServiceImpl implements ItemDescService {
    @Autowired
    private TbItemDescMapper tbItemDescMapper;
    /*@Resource
    private RedisTemplate<Object,Object> redisTemplate;*/
    @Override
    public TaotaoResult getItemDesc(Long id) {
        /*TbItemDesc tbItemDesc = (TbItemDesc) redisTemplate.opsForValue().get("id");
        if (tbItemDesc == null){
           tbItemDesc = tbItemDescMapper.selectByPrimaryKey(id);
           redisTemplate.opsForValue().set("id",tbItemDesc,1,TimeUnit.DAYS);
        }
            return TaotaoResult.ok(tbItemDesc);
    }*/
        try {
            TbItemDesc tbItemDesc = tbItemDescMapper.selectByPrimaryKey(id);
            return TaotaoResult.ok(tbItemDesc);
        } catch (Exception e) {
            return TaotaoResult.error("查询商品描述信息失败");
        }
    }

}
