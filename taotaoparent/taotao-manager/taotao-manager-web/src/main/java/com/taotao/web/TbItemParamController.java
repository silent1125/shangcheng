package com.taotao.web;

import com.taotao.commons.EasyUIResult;
import com.taotao.service.TbItemParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("item")
public class TbItemParamController {

    @Autowired
    private TbItemParamService tbItemParamService;
    @RequestMapping("/param/list")
    public EasyUIResult findItemParam(@RequestParam("page") int page, @RequestParam("rows") int rows){
        EasyUIResult result = tbItemParamService.findItemParamList(page, rows);

        return result;
    }
}
