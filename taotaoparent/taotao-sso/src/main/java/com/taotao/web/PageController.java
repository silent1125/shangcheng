package com.taotao.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

    @RequestMapping("/")
    public String toPage(String redirect, Model model) {
        model.addAttribute("redirect", redirect);
        return "login";
    }

    @RequestMapping("/user/showRegister")
    public String toRegister() {
        return "register";
    }

    @RequestMapping("/user/showLogin")
    public String toLogin(String redirect, Model model) {

        model.addAttribute("redirect", redirect);

        return "login";
    }
}
