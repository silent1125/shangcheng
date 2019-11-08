package com.taotao.web;

import com.taotao.commons.EUTreeNode;
import com.taotao.commons.EasyUIResult;
import com.taotao.commons.TaotaoResult;
import com.taotao.service.TbContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/content/category")
public class TbContentCategoryController {

    @Autowired
    private TbContentCategoryService tbContentCategoryService;
    @RequestMapping("list")
    public List<EUTreeNode> getContentCategoryList(@RequestParam(value="id", defaultValue="0")long parentId){

        List<EUTreeNode> list = tbContentCategoryService.selectByExample(parentId);
        return list;
    }

@RequestMapping("create")
    public TaotaoResult addNode(long parentId,String name){

        TaotaoResult result = tbContentCategoryService.addNode(parentId,name);
        return result;
    }

    @RequestMapping("delete")
    public TaotaoResult deleteAll(Long id){
        TaotaoResult result = tbContentCategoryService.deleteNode(id);
        return result;
    }
}
