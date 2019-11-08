package com.taotao.web;

import com.taotao.commons.SearchResult;
import com.taotao.commons.SystemConstants;
import com.taotao.commons.TaotaoResult;
import com.taotao.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class SearchController {
    @Autowired
    private SearchService searchService;
    @RequestMapping("/search")
    public TaotaoResult search(@RequestParam("q") String keywords, @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "30") Integer pageSize) {
        System.out.println("********");
        SearchResult result = searchService.search(keywords, pageNum, pageSize);
        if(result == null) {
            return TaotaoResult.build(SystemConstants.TAOTAO_RESULT_STATUS_ERROR, "搜索异常");
        }
        System.out.println("*********");
        System.out.println(TaotaoResult.ok(result).getStatus());
        return TaotaoResult.ok(result);


    }

}
