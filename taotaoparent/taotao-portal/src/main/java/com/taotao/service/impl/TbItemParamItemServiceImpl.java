package com.taotao.service.impl;

import com.taotao.commons.HttpUtil;
import com.taotao.commons.JsonUtils;
import com.taotao.commons.TaotaoResult;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.service.TbItemParamItemService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TbItemParamItemServiceImpl implements TbItemParamItemService {
    @Override
    public String getItemParam(Long id) {

        String jsonResult = HttpUtil.doGet("http://localhost:8081/item/param/" + id);
        TaotaoResult taotaoResult = TaotaoResult.formatToPojo(jsonResult, TbItemParamItem.class);
        if (taotaoResult.getStatus().equals(200)) {
            TbItemParamItem itemParam = (TbItemParamItem) taotaoResult.getData();
            String html = buildHtmlFromTbItemParamItem2(itemParam);
            return html;
        }

        return "<span>暂无商品规格参数<span>";
    }


    private String buildHtmlFromTbItemParamItem2(TbItemParamItem itemParamItem) {
        StringBuilder sb = new StringBuilder();
        sb.append("<table cellpadding=\"0\" cellspacing=\"1\" width=\"100%\" border=\"0\" class=\"Ptable\">");
        sb.append("    <tbody>");
        // 把数据库中存储的商品规格参数字符串转换成Java对象，方便下面组装html
        List<Map> paramDataItemList = JsonUtils.jsonToList(itemParamItem.getParamData(), Map.class);
        // 第一层循环是拼一个规格参数主体块的html
        for(Map itemParamMap : paramDataItemList) {
            String groupName = (String) itemParamMap.get("group");
            sb.append("<tr>");
            sb.append("<th class=\"tdTitle\" colspan=\"2\">"+groupName+"</th>");
            sb.append("</tr>");
            List<Map> params = (List<Map>) itemParamMap.get("params");
            // 第二层循环是拼接一个规格参数主体下面每一项规格参数的html
            for (Map map2 : params) {
                sb.append("<tr>");
                sb.append("<td class=\"tdTitle\">"+map2.get("k")+"</td>");
                sb.append("<td>"+map2.get("v")+"</td>");
                sb.append("</tr>");
            }
        }
        sb.append("</tbody>");
        sb.append("</table>");
        return sb.toString();
    }




    }

