package com.taotao.web;

import com.taotao.pojo.TbItemExt;
import com.taotao.service.TbItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TbItemController {
    @Autowired
    private TbItemService tbItemService;
    @RequestMapping("/item/{id}")
    public String toItemDetailsPage(@PathVariable("id") Long id, Model model){
    TbItemExt tbItemExt = tbItemService.getTbItemInfo(id);
    model.addAttribute("item", tbItemExt);
    return "item";
}

}
