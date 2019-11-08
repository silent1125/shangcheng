package com.taotao.web;

import com.taotao.commons.TaotaoResult;
import com.taotao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/item")
public class ItemController {
    @Autowired
    private ItemService itemService;
    @Cacheable("get_item_detail")
    @RequestMapping("/get_item_detail/{id}")
    public TaotaoResult getItemDetails(@PathVariable Long id){
    TaotaoResult result = itemService.getItemDetails(id);
        return result;
    }

}
