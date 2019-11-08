package com.taotao.web;


import com.taotao.commons.TaotaoResult;
import com.taotao.pojo.TbItemDesc;
import com.taotao.service.TbItemDescService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

@RestController

public class TbItemDescController {
    @Autowired
    private TbItemDescService tbItemDescService;
    @RequestMapping("item/desc/{ids}")
    public TaotaoResult queryItemDesc(@PathVariable("ids") long itemId){


        return tbItemDescService.selectByPrimaryKey(itemId);

    }
}
