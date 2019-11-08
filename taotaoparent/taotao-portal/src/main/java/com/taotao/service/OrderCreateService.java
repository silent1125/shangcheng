package com.taotao.service;

import com.taotao.commons.TaotaoResult;
import com.taotao.pojo.Order;


public interface OrderCreateService {
    public TaotaoResult createService(Order order);
}
