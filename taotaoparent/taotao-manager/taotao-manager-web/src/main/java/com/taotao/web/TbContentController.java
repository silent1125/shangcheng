package com.taotao.web;

import com.taotao.commons.EasyUIResult;
import com.taotao.commons.TaotaoResult;
import com.taotao.pojo.TbContent;
import com.taotao.service.TbContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("content")
public class TbContentController {

    @Autowired
    private TbContentService tbContentService;

    @RequestMapping("/listAll")
    public List<TbContent> listAll(){
        return tbContentService.listAll();
    }


    @RequestMapping("/query/list")

    public EasyUIResult getContentList(Long categoryId, Integer page, Integer rows) throws Exception {
        EasyUIResult result = tbContentService.getContentList(categoryId, page, rows);

        return result;

    }

    @RequestMapping("/save")
    public TaotaoResult addContent(TbContent tbContent){
        TaotaoResult result = tbContentService.addContent(tbContent);
        return result;
    }

}
