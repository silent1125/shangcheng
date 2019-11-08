package com.taotao.service.impl;

import com.taotao.commons.HttpUtil;
import com.taotao.commons.SystemConstants;
import com.taotao.commons.TaotaoResult;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemExt;
import com.taotao.service.TbItemService;
import org.springframework.stereotype.Service;

@Service
public class TbItemServiceImpl implements TbItemService {

    @Override
    public TbItemExt getTbItemInfo(Long id) {

        String resultJson = HttpUtil.doGet("http://localhost:8081/item/get_item_detail/" + id);
        TaotaoResult taotaoResult = TaotaoResult.formatToPojo(resultJson, TbItem.class);
        if(taotaoResult.getStatus().equals(SystemConstants.TAOTAO_RESULT_STATUS_OK)) {
            TbItem tbItem = (TbItem) taotaoResult.getData();
            return parseTbItem2Ext(tbItem, new TbItemExt());
        }

        return null;
    }

    /**
     * 将TbItem对象转换成前端页面需要的TbItemExt对象
     * @param src
     * @param dst
     * @return
     */
    private TbItemExt parseTbItem2Ext(TbItem src, TbItemExt dst) {
        dst.setId(src.getId());
        dst.setCreated(src.getCreated());
        dst.setUpdated(src.getUpdated());
        dst.setStatus(src.getStatus());
        dst.setBarcode(src.getBarcode());
        dst.setCid(src.getCid());
        dst.setImage(src.getImage());
        dst.setPrice(src.getPrice());
        dst.setNum(src.getNum());
        dst.setTitle(src.getTitle());
        dst.setSellPoint(src.getSellPoint());
        return dst;
    }


    }

