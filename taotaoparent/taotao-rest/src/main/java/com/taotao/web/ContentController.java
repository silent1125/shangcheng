package com.taotao.web;

import com.taotao.commons.TaotaoResult;
import com.taotao.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("rest")
public class ContentController {
    @Autowired
    private ContentService contentService;

    @RequestMapping("/category/{cid}")
    @ResponseBody
    public TaotaoResult getContentList(@PathVariable Long cid) {
        TaotaoResult result = null;
        try {
            result = contentService.getContentList(cid);
        } catch (Exception e) {
            e.printStackTrace();
            return TaotaoResult.build(500, e.getMessage());
        }
        return result;
    }

}
