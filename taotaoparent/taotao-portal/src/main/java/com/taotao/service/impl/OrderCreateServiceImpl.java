package com.taotao.service.impl;

import com.taotao.commons.HttpClient;
import com.taotao.commons.JsonUtils;
import com.taotao.commons.TaotaoResult;
import com.taotao.pojo.Order;

import com.taotao.service.OrderCreateService;
import org.springframework.stereotype.Service;

@Service
public class OrderCreateServiceImpl implements OrderCreateService {

    //@Value("${ORDER_BASE_URL}")
    //private String ORDER_BASE_URL = "http://localhost:8085/order/create";

   /* //@Value("${ORDER_CREATE_URL}")
    private String ORDER_CREATE_URL = "/order/create";*/


    @Override
        public TaotaoResult createService(Order order) {

            //把pojo转换成json数据
            String json = JsonUtils.objectToJson(order);
            //调用订单系统服务提交订单
        System.out.println(json);

        String resultStr = HttpClient.doPostJson("http://localhost:8085/order/create", json);
            //转换成java对象
            TaotaoResult taotaoResult = TaotaoResult.format(resultStr);

            return taotaoResult;
        }


    }

