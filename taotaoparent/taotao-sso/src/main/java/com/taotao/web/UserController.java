package com.taotao.web;

import com.taotao.commons.JsonUtils;
import com.taotao.commons.TaotaoResult;
import com.taotao.pojo.TbUser;
import com.taotao.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;


    /**
     * 注册 信息检查
     * @param param
     * @param type
     * @return
     */
    @RequestMapping("/check/{param}/{type}")
    public TaotaoResult checkData(@PathVariable String param, @PathVariable Integer type) {
        TaotaoResult taotaoResult = userService.checkData(param, type);
        return taotaoResult;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public TaotaoResult registerUser(TbUser tbUser) {
        TaotaoResult taotaoResult = userService.register(tbUser);
        return taotaoResult;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public TaotaoResult registerUser(String username, String password, HttpServletRequest req, HttpServletResponse resp) {
        TaotaoResult taotaoResult = userService.login(username, password, req, resp);
        return taotaoResult;
    }

    @CrossOrigin(origins = {"localhost", "127.0.0.1", "www.taotao.com"})
    @RequestMapping(value = "/token/{token}", method = RequestMethod.GET)
    public Object getUserByToken(@PathVariable String token, @RequestParam(required = false) String callback) {
        TaotaoResult taotaoResult = userService.getUserByToken(token);
        if(!StringUtils.isBlank(callback)) {
            return callback+"(" + JsonUtils.objectToJson(taotaoResult) +");";
        }
        return taotaoResult;
    }

}
