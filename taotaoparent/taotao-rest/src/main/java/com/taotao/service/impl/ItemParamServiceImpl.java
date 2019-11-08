package com.taotao.service.impl;

import com.taotao.commons.ExceptionUtil;
import com.taotao.commons.TaotaoResult;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.mapper.TbItemParamMapper;
import com.taotao.pojo.TbItemParam;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.pojo.TbItemParamItemExample;
import com.taotao.service.ItemParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemParamServiceImpl implements ItemParamService {
    @Autowired
    private TbItemParamItemMapper tbItemParamItemMapper;
    //@Cacheable("getItemParam")
    @Override
    public TaotaoResult getItemParam(Long id) {

        TbItemParamItem tbItemParamItem = null;
        try {
            TbItemParamItemExample condition = new TbItemParamItemExample();
            condition.createCriteria().andItemIdEqualTo(id);

            List<TbItemParamItem> tbItemParamItemList = tbItemParamItemMapper.selectByExampleWithBLOBs(condition);
            System.out.println("-----------");
            System.out.println(tbItemParamItemList);
            if(tbItemParamItemList == null || tbItemParamItemList.size() < 1) {
                return TaotaoResult.error("没有查询到ID为: " + id+"的商品规格参数信息");
            }
            tbItemParamItem = tbItemParamItemList.get(0);
            return TaotaoResult.ok(tbItemParamItem);
        } catch (Exception e) {
            return TaotaoResult.error("获取商品规格参数失败");
        }

    }
}
