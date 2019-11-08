package com.taotao.service.impl;

import com.taotao.commons.*;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.service.CartService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Service
//@PropertySource("classpath:rest.properties")
public class CartServiceImpl implements CartService {
    //服务url
    //@Value("${REST_BASE_URL}")
    private String REST_BASE_URL = "http://localhost:8081/item/";
    //商品服务url
    //@Value("${ITEMS_ITEM_URL}")
    private String ITEMS_ITEM_URL = "get_item_detail/";
    //COOKIE中购物车商品对应的key
    //@Value("${CART_ITEMS_LIST_KEY}")
    private String CART_ITEMS_LIST_KEY = "TbItemId";
    //购物车cookie生存期
    //@Value("${CART_ITEMS_EXPIRE_TIME}")
    private Integer CART_ITEMS_EXPIRE_TIME = 2000;
    /**
     * 添加购物车商品
     * <p>Title: addItem</p>
     * <p>Description: </p>
     * @param itemId
     * @param request
     * @param response
     * @return

*/
   /* @Autowired
    private TbItemMapper tbItemMapper;*/
    @Override
    public TaotaoResult addItem(Long itemId, HttpServletRequest request, HttpServletResponse response) {
        //根据商品id查询商品信息
        TbItem tbItem = getItemById(itemId);
        if (tbItem == null) {
            return TaotaoResult.build(500, "未查询到该商品信息");
        }
        //取cookie中购物车商品列表
        /*List<TbItem> cartItems = getItemListFromCookie(request);*/
        List<TbItem> cartItems = getItemListFromCookie(request);

        //判断该商品是否存在于购物车中
        boolean itemExists = false;
        for (TbItem i : cartItems) {
            if (i.getId().longValue() == itemId.longValue()) {
                //购物车中有此商品
                i.setNum(i.getNum() + 1);
                itemExists = true;
                break;
            }

        }
        //如果商品不存在于购物车则向购物车商品列表中添加一个商品
        if (! itemExists) {
            //设置数量为1
            tbItem.setNum(1);
            //把商品添加到购物车
            cartItems.add(tbItem);
        }
        //把购物车信息写入cookie中
        CookieUtils.setCookie(request, response, CART_ITEMS_LIST_KEY, JsonUtils.objectToJson(cartItems), CART_ITEMS_EXPIRE_TIME, true);

        return TaotaoResult.ok(cartItems);
    }



    private TbItem getItemById(Long itemId) {
        //根据商品id查询商品信息
        String resultStr = HttpClient.doGet(REST_BASE_URL + ITEMS_ITEM_URL + itemId);
        //转换成taotaoResult
        TaotaoResult result = TaotaoResult.formatToPojo(resultStr, TbItem.class);
        //取商品信息
        TbItem item  = null;
        if (result.getStatus() == 200) {
            item = (TbItem) result.getData();
        }

        return item;
    }

    private List<TbItem> getItemListFromCookie(HttpServletRequest request) {
        //取cookie中购物车商品列表
        String cartItemsStr = CookieUtils.getCookieValue(request, CART_ITEMS_LIST_KEY, true);
        //如果不为空那么就转换成java对象
        List<TbItem> cartItems = null;
        if (!StringUtils.isBlank(cartItemsStr)) {
            cartItems = JsonUtils.jsonToList(cartItemsStr, TbItem.class);
        } else {
            cartItems = new ArrayList<>();
        }
        return cartItems;
    }

    @Override
    public List<TbItem> getCartItemsList(HttpServletRequest request) {
        // 从cookie中取商品列表
        List<TbItem> itemsList = getItemListFromCookie(request);
        return itemsList;

    }

    @Override
    public TaotaoResult changeItemNum(long itemId, int num, HttpServletRequest request,
                                      HttpServletResponse response) {
        //从cookie中取商品列表
        List<TbItem> list = getItemListFromCookie(request);
        //从商品列表中找到要修改数量的商品
        for (TbItem item : list) {
            if (item.getId() == itemId) {
                //找到商品，修改数量
                item.setNum(num);
                break;
            }
        }
        //把商品信息写入cookie
        CookieUtils.setCookie(request, response, CART_ITEMS_LIST_KEY, JsonUtils.objectToJson(list), CART_ITEMS_EXPIRE_TIME, true);

        return TaotaoResult.ok();

    }

    @Override
    public List<TbItem> deleteItem(Long itemId, HttpServletRequest request, HttpServletResponse response) {
        List<TbItem> itemsList = getCartItemsList(request);
        // 找到购物车中的商品，并删除之
        for (TbItem item : itemsList) {
            if (item.getId().longValue() == itemId.longValue()) {
                itemsList.remove(item);
                break;
            }
        }
        // 更新cookie中的购物车数据
        CookieUtils.setCookie(request, response, CART_ITEMS_LIST_KEY,
                JsonUtils.objectToJson(itemsList), CART_ITEMS_EXPIRE_TIME, true);
        return itemsList;

    }

}
