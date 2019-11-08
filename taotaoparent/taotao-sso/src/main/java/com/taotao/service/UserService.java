package com.taotao.service;

import com.taotao.commons.TaotaoResult;
import com.taotao.pojo.TbUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface UserService {
    //验证信息
    TaotaoResult checkData(String param, Integer type);

    //注册
    TaotaoResult register(TbUser tbUser);

    //登录
    TaotaoResult login(String username, String password, HttpServletRequest request,
                       HttpServletResponse response);

    //登录验证
    TaotaoResult getUserByToken(String token);
}
