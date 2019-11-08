package com.taotao.web;

import com.taotao.commons.EUTreeNode;
import com.taotao.service.TbItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("item")
public class TbItemCatController {
    @Autowired
    private TbItemCatService tbItemCatService;
    //查找所有商品类目 如果传入父级节点，查找指定节点下的子节点
@RequestMapping("cat/list")
    public List<EUTreeNode> itemCatList(@RequestParam(value = "id",defaultValue = "0")  long parentId){
       return tbItemCatService.itemCatList(parentId);
    }
}
