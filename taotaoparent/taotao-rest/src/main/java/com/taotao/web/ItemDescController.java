package com.taotao.web;

import com.taotao.commons.TaotaoResult;
import com.taotao.service.ItemDescService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("item")
@Cacheable("getItemDesc")
public class ItemDescController {
    @Autowired
    private ItemDescService itemDescService;
    @RequestMapping("/desc/{id}")
    public TaotaoResult getItemDesc(@PathVariable Long id){
        TaotaoResult result = itemDescService.getItemDesc(id);
        return result;
    }
}
