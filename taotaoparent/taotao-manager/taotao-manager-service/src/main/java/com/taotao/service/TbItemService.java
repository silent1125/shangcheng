package com.taotao.service;

import com.taotao.commons.EasyUIResult;
import com.taotao.commons.TaotaoResult;
import com.taotao.pojo.TbItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TbItemService {
    //按照分页查找所有商品信息
    public EasyUIResult findItemList(int page,int rows);

    //根据id删除数据
    public TaotaoResult deleteItem(List ids);

    // 商品状态修改(正常,下架,删除)
    TaotaoResult updateItemStatusPrimaryKey(List ids);

    //上架
    TaotaoResult updateItemStatusPrimaryKey2( List ids);

    //新增商品
    public TaotaoResult saveTbItem(TbItem tbItem,String desc);
/*
    //按照id查找商品信息来编辑
    TbItem selectByPrimaryKey(Long id);*/

    //更新商品信息
    TaotaoResult updateItem(TbItem tbItem);




}
