package com.taotao.service;

import com.taotao.commons.TaotaoResult;
import com.taotao.pojo.TbItem;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface CartService {
    TaotaoResult addItem(Long itemId, HttpServletRequest request, HttpServletResponse response);

    //展示购物车商品
    List<TbItem> getCartItemsList(HttpServletRequest request);

    //修改商品数量
    TaotaoResult changeItemNum(long itemId, int num, HttpServletRequest request,
                               HttpServletResponse response);

    //删除购物车商品
    List<TbItem> deleteItem(Long itemId, HttpServletRequest request, HttpServletResponse response);
}
