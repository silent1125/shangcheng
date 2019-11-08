package com.taotao.web;

import com.taotao.commons.TaotaoResult;
import com.taotao.service.ItemParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("item")
public class ItemParamController {
    @Autowired
    private ItemParamService itemParamService;
    @RequestMapping("/param/{id}")
    @Cacheable("getItemParam")
    public TaotaoResult getItemParam(@PathVariable Long id){
        TaotaoResult result = itemParamService.getItemParam(id);
        return result;
    }
}
