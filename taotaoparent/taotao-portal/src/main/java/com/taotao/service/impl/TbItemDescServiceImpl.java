package com.taotao.service.impl;

import com.taotao.commons.HttpUtil;
import com.taotao.commons.TaotaoResult;
import com.taotao.pojo.TbItemDesc;
import com.taotao.service.TbItemDescService;
import org.springframework.stereotype.Service;

@Service
public class TbItemDescServiceImpl implements TbItemDescService {
    @Override
    public String getTbItemDesc(Long id) {
        String jsonResult = HttpUtil.doGet("http://localhost:8081/item/desc/"+id);
        TaotaoResult taotaoResult = TaotaoResult.formatToPojo(jsonResult, TbItemDesc.class);
        if(taotaoResult.getStatus().equals(200)) {
            TbItemDesc itemDesc = (TbItemDesc) taotaoResult.getData();
            return itemDesc.getItemDesc();
        } else {

            return "<span>暂无描述</span>";
        }
    }


    }

