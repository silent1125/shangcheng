package com.taotao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.commons.EasyUIResult;
import com.taotao.commons.IDUtils;
import com.taotao.commons.TaotaoResult;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.service.TbItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TbItemServiceImpl implements TbItemService {
    @Autowired
    private TbItemMapper tbItemMapper;
    @Autowired
    private TbItemDescMapper tbItemDescMapper;
    @Override
    public EasyUIResult findItemList(int page, int rows) {
        //利用分页对象设置
        PageHelper.startPage(page,rows);

        List<TbItem> itemList = tbItemMapper.selectByExample(null);

        PageInfo<TbItem> pageInfo = new PageInfo<>(itemList);

        long total = pageInfo.getTotal();//总共的数据行数

        //封装结果集
        EasyUIResult result = new EasyUIResult(total,itemList);

        return result;
    }

    @Override
    public TaotaoResult deleteItem(List ids) {
        int row = tbItemMapper.deleteByPrimaryKey(ids);
        if (row > 0) {
            return TaotaoResult.build(200, "ok", null);
        } else {
            return TaotaoResult.build(500, "error", null);
        }
    }

    @Override
    public TaotaoResult updateItemStatusPrimaryKey(List ids) {
        System.out.println(ids);
        int row = tbItemMapper.updateItemStatusPrimaryKey(ids);
        if(row > 0){
            return TaotaoResult.build(200,"ok",null);
        }else {
            return TaotaoResult.build(500,"error",null);
        }


    }

    @Override
    public TaotaoResult updateItemStatusPrimaryKey2(List ids) {
        int row = tbItemMapper.updateItemStatusPrimaryKey2(ids);
        if (row > 0) {
            return TaotaoResult.build(200, "ok", null);
        } else {
            return TaotaoResult.build(500, "error", null);
        }
    }

    @Override
    public TaotaoResult saveTbItem(TbItem tbItem,String desc) {
        //生成商品id
        long itemId = IDUtils.genItemId();
        tbItem.setId(itemId);
        tbItem.setStatus((byte)1);
        tbItem.setCreated(new Date());
        tbItem.setUpdated(new Date());
        //插入到商品表
        tbItemMapper.insert(tbItem);

        TbItemDesc tbItemDesc = new TbItemDesc();
        tbItemDesc.setItemId(itemId);
        tbItemDesc.setItemDesc(desc);
        tbItemDesc.setCreated(new Date());
        tbItemDesc.setUpdated(new Date());
        //插入商品描述
        tbItemDescMapper.insert(tbItemDesc);
        return TaotaoResult.ok();
    }

    @Override
    public TaotaoResult updateItem(TbItem tbItem) {
        long ids = tbItem.getId();
        int n = tbItemMapper.updateByPrimaryKeySelective(tbItem);
        if (n > 0) {
            return TaotaoResult.build(200, "ok", null);
        } else {
            return TaotaoResult.build(500, "error", null);
        }
    }
}
