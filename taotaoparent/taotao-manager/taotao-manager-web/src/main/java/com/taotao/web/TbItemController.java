package com.taotao.web;

import com.taotao.commons.EasyUIResult;
import com.taotao.commons.TaotaoResult;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.service.TbItemDescService;
import com.taotao.service.TbItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("item")
public class TbItemController {
    @Autowired
    private TbItemService tbItemService;
    @Autowired
    private TbItemDescService tbItemDescService;
    @RequestMapping("/list")
    public EasyUIResult findItemList(@RequestParam("page") int page, @RequestParam("rows") int rows){

        EasyUIResult result = tbItemService.findItemList(page,rows);
        return result;

    }
   /* @RequestMapping("/delete")
    public String deleteItem(long id){
        int n = 0;
        String status = "200";
TaotaoResult taotaoResult = new TaotaoResult();
taotaoResult.setStatus(200);
       n = tbItemService.deleteItem(id);
       if (n != 0){
           return status;
       }else {
           return "false";
       }
    }*/


    @RequestMapping("/delete")
    public TaotaoResult deleteItem(@RequestParam("ids") List ids){

        return tbItemService.deleteItem(ids);


    }


    @RequestMapping("/instock")
    public TaotaoResult updateItem(@RequestParam("ids") List ids){
        return tbItemService.updateItemStatusPrimaryKey( ids);
    }

    @RequestMapping("/reshelf")
    public TaotaoResult updateItem2(@RequestParam("ids") List ids){
        return tbItemService.updateItemStatusPrimaryKey2( ids);
    }

@RequestMapping("/save")
    public TaotaoResult saveItem(TbItem tbItem,String desc){
        return tbItemService.saveTbItem(tbItem,desc);
    }
@Transactional
    @RequestMapping("/update")
    public TaotaoResult updateItemAndItemDesc(TbItem tbItem, String desc){
    TbItemDesc tbItemDesc = new TbItemDesc();
    tbItemDesc.setItemId(tbItem.getId());
    tbItemDesc.setItemDesc(desc);
        int n = tbItemDescService.updateItemDesc(tbItemDesc).getStatus();
        int m = tbItemService.updateItem(tbItem).getStatus();
        if (m ==200 && n ==200){
            return TaotaoResult.build(200,"ok",null);
        }else {
            return TaotaoResult.build(500,"error",null);
        }

    }


}
