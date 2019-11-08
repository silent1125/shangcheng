package com.taotao.service;

import com.taotao.commons.TaotaoResult;
import com.taotao.pojo.TbItemDesc;

public interface TbItemDescService {
    //查询商品描述
    TaotaoResult selectByPrimaryKey(Long itemId);

    //更新商品描述
    TaotaoResult updateItemDesc(TbItemDesc tbItemDesc);
}
