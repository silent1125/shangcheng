package com.taotao.web;

import com.taotao.commons.JsonUtils;
import com.taotao.commons.TaotaoResult;
import com.taotao.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/itemcat")
public class ItemCatController {

    @Autowired
    private ItemCatService itemCatService;
    @CrossOrigin
    @RequestMapping("/all")
    public Object getItemCat(String callback) {
        TaotaoResult taotaoResult = itemCatService.getItemCat();
        return callback + "(" + JsonUtils.objectToJson(taotaoResult) + ")";
    }

}
