package com.taotao.service.impl;

import com.taotao.commons.TaotaoResult;
import com.taotao.mapper.TbOrderItemMapper;
import com.taotao.mapper.TbOrderMapper;
import com.taotao.mapper.TbOrderShippingMapper;
import com.taotao.pojo.TbOrder;
import com.taotao.pojo.TbOrderItem;
import com.taotao.pojo.TbOrderShipping;
import com.taotao.service.OrderService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private TbOrderMapper orderMapper;
    @Autowired
    private TbOrderItemMapper orderItemMapper;
    @Autowired
    private TbOrderShippingMapper orderShippingMapper;
    //redis中的订单key
    @Value("order")
    private String ORDER_ID_KEY;
    //@Value("100001")
    private Long ORDER_BEGIN_ID = Long.parseLong("8085");

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public TaotaoResult createOrder(TbOrder order, List<TbOrderItem> itemList, TbOrderShipping orderShipping) {

        //订单表
        //生成订单号
        String orderIdStr = (String) stringRedisTemplate.opsForValue().get(ORDER_ID_KEY);
        Long orderId = null;
        if (StringUtils.isBlank(orderIdStr)) {
            //如果redis中没有订单号使用初始订单号初始化
            stringRedisTemplate.opsForValue().set(ORDER_ID_KEY, ORDER_BEGIN_ID.toString());
            orderId = ORDER_BEGIN_ID;
        } else {
            //生成订单号
            orderId = stringRedisTemplate.opsForValue().increment(ORDER_ID_KEY);
        }
        //设置订单号
        order.setOrderId(orderId.toString());
        Date date = new Date();
        //订单创建时间
        order.setCreateTime(date);
        //订单更新时间
        order.setUpdateTime(date);
        //插入订单表
        orderMapper.insert(order);
        //插入订单商品表
        for (TbOrderItem tbOrderItem : itemList) {
            //取订单商品id
            Long orderItemId = stringRedisTemplate.opsForValue().increment("ORDER_ITEM_ID");
            tbOrderItem.setId(orderItemId.toString());
            tbOrderItem.setOrderId(orderId.toString());
            //添加到订单商品表
            orderItemMapper.insert(tbOrderItem);
        }
        //插入物流表
        orderShipping.setOrderId(orderId.toString());
        orderShipping.setCreated(date);
        orderShipping.setUpdated(date);
        orderShippingMapper.insert(orderShipping);

        return TaotaoResult.ok(orderId.toString());
    }

}
