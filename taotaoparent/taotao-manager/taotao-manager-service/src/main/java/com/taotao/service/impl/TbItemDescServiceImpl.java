package com.taotao.service.impl;

import com.taotao.commons.TaotaoResult;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.pojo.TbItemDesc;
import com.taotao.service.TbItemDescService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class TbItemDescServiceImpl implements TbItemDescService {
    @Autowired
    TbItemDescMapper tbItemDescMapper;
    @Override
    public TaotaoResult selectByPrimaryKey(Long itemId) {
        TbItemDesc tbItemDesc = tbItemDescMapper.selectByPrimaryKey(itemId);

        /*List<TbItemDesc> tbItemDescList = new ArrayList<>();
        tbItemDescList.add(tbItemDesc);*/
        if (tbItemDesc == null) {
            return null;
        } else {
            return TaotaoResult.build(200, "ok", tbItemDesc);
        }


    }

    @Override
    public TaotaoResult updateItemDesc(TbItemDesc tbItemDesc) {
long ids = tbItemDesc.getItemId();
        int n = tbItemDescMapper.updateByPrimaryKeySelective(tbItemDesc);
        if (n > 0){
            return TaotaoResult.build(200,"ok",null);
        }else {
            return TaotaoResult.build(200,"ok",null);
        }

    }
}
