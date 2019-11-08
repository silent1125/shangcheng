package com.taotao.web;

import com.taotao.commons.CookieUtils;

import com.taotao.commons.TaotaoResult;
import com.taotao.pojo.*;
import com.taotao.service.CartService;
import com.taotao.service.OrderCreateService;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderCreateController {


    @Autowired
    private CartService cartService;



    private String CART_ITEMS_LIST_KEY = "TbItemId";

    @RequestMapping("/order-cart")
    public String showOrderCart(HttpServletRequest request, TbUser user, Model model) {
        // 根据用户信息，取出用户的收货地址列表
        // 本项目中使用静态数据模拟。。。。
        String cookie = CookieUtils.getCookieValue(request,CART_ITEMS_LIST_KEY);
        if(cookie != null && !cookie.equals("")){
            // 从 cookie 中把商品列表取出来
            List<TbItem> itemsList = cartService.getCartItemsList(request);
            model.addAttribute("cartList", itemsList);
            return "order-cart";
        }else{
            model.addAttribute("message","没有商品需要提交");
            return "error/exception";
        }
    }




    @Autowired
    private OrderCreateService orderCreateService;


    @RequestMapping(value="/create", method= RequestMethod.POST)
    public String createOrder(HttpServletRequest request, Order order, Model model) {
        //从request中取用户信息
        TbUser user = (TbUser) request.getAttribute("user");
        //String token = CookieUtils.getCookieValue(request,"TT_TOKEN");
        /*String rediskey = "user"+token;
        System.out.println(redisTemplate.opsForValue().get(rediskey));
        //TbUser user =  JsonUtils.jsonToPojo(redisTemplate.opsForValue().get(rediskey),TbUser.class);

        TbUser user =  JsonUtils.jsonToPojo(redisTemplate.opsForValue().get(rediskey).toString(),TbUser.class);*/

        System.out.println(user);

       /* String userJson = (String) redisTemplate.opsForValue().get(rediskey);
        System.out.println(userJson);*/

         //TbUser user = JsonUtils.jsonToPojo(userJson, TbUser.class);
        /*TbUser user = (TbUser)request.getAttribute("user");*/
        //把用户信息补充到order对象中
        System.out.println(user.getId());

        order.setUserId(user.getId());
        order.setBuyerNick(user.getUsername());

        System.out.println(order.getOrderId());

        System.out.println(order.getOrderItems());
        //提交订单
        TaotaoResult result = null;
        //orderService.createService(order);
        try {
            result = orderCreateService.createService(order);
           // result = orderService.createService(order);
            //订单创建成功
            if(result.getStatus() == 200) {
                model.addAttribute("orderId", result.getData());
                model.addAttribute("payment", order.getPayment());
                //两天后送达
                DateTime dateTime = new DateTime();
                dateTime = dateTime.plusDays(2);
                model.addAttribute("date", dateTime.toString("yyyy-MM-dd"));
                return "success";
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = TaotaoResult.build(500, "error");
        }
        //订单创建失败
        model.addAttribute("message", result.getMsg());
        return "error/exception";

    }

}
