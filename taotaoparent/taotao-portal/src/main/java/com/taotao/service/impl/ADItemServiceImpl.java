package com.taotao.service.impl;

import com.sun.deploy.net.HttpUtils;
import com.taotao.commons.HttpUtil;
import com.taotao.commons.JsonUtils;
import com.taotao.commons.TaotaoResult;
import com.taotao.pojo.ADItem;
import com.taotao.pojo.TbContent;
import com.taotao.service.ADItemService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@PropertySource("classpath:/rest.properties")
@Service
public class ADItemServiceImpl implements ADItemService {
    @Value("${REST_BASE_URL}")
    private String REST_BASE_URL;
    @Value("${INDEX_AD1_URL}")
    private String INDEX_AD1_URL;

    /*@Autowired
    private ADItemService adItemService;*/
   /* @Autowired
    private HttpUtils httpUtils;*/

    @Override
    public String getAdItemList() {
        //调用服务层的服务查询打广告位的数据
        String result = HttpUtil.doGet(REST_BASE_URL + INDEX_AD1_URL);
        //把json数据转换成对象
        TaotaoResult taotaoResult = TaotaoResult.formatToList(result, TbContent.class);
        List<ADItem> itemList = new ArrayList<>();
        if (taotaoResult.getStatus() == 200 ) {
            List<TbContent> contentList = (List<TbContent>) taotaoResult.getData();
            for (TbContent tbContent : contentList) {
                ADItem item = new ADItem();
                item.setHeight(240);
                item.setWidth(670);
                item.setSrc(tbContent.getPic());
                item.setHeightB(240);
                item.setWidthB(550);
                item.setSrcB(tbContent.getPic2());
                item.setAlt(tbContent.getTitleDesc());
                item.setHref(tbContent.getUrl());
                itemList.add(item);
            }

        }
        return JsonUtils.objectToJson(itemList);


    }
}
