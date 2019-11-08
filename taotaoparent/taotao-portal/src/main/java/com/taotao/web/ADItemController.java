package com.taotao.web;

import com.taotao.service.ADItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ADItemController {
@Autowired
private ADItemService adItemService;
    @RequestMapping("/")
    public String showIndex(Model model) throws Exception {
        String adResult = adItemService.getAdItemList();
        model.addAttribute("ad1", adResult);

        return "index";
    }

}
