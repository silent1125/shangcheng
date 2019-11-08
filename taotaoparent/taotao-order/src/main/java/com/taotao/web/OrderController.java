package com.taotao.web;

import com.taotao.commons.ExceptionUtil;
import com.taotao.commons.TaotaoResult;
import com.taotao.pojo.Order;
import com.taotao.pojo.TbOrder;
import com.taotao.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/create", method= RequestMethod.POST)
    @ResponseBody
    public TaotaoResult createOrder(@RequestBody Order order) {
        TaotaoResult result = null;
        try {
            result = orderService.createOrder(order, order.getOrderItems(), order.getOrderShipping());
        } catch (Exception e) {
            e.printStackTrace();
            return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
        }
        return result;
    }




}
