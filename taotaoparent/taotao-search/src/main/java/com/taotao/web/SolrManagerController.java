package com.taotao.web;

import com.taotao.commons.TaotaoResult;
import com.taotao.service.TbItemSolrManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/solr")
public class SolrManagerController {
    @Autowired
    private TbItemSolrManagerService solrManagerService;
    @CrossOrigin
    @RequestMapping("/item")
    @ResponseBody
    public TaotaoResult importTbItem2Solr() {

        System.out.println("**************");
        TaotaoResult result = solrManagerService.importAllTbItem2Solr();
        return result;
    }

}
