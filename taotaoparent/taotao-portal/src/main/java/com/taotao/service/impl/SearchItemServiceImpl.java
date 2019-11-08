package com.taotao.service.impl;

import com.taotao.commons.HttpUtil;
import com.taotao.commons.SearchResult;
import com.taotao.commons.SystemConstants;
import com.taotao.commons.TaotaoResult;
import com.taotao.service.SearchItemService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class SearchItemServiceImpl implements SearchItemService {

        @Value("http://localhost:8083")
        private String searchUrl;

        @Override
        public SearchResult search(String keywords, Integer pageNum, Integer pageSize) {
            Map<String, String> params = new HashMap<>();
            params.put("q", keywords);
            params.put("pageNum", pageNum+"");
            params.put("pageSize", pageSize+"");
            //TaotaoResult searchResultJson = HttpUtil.doPost(searchUrl+"/search", params);
            String searchResultJson = HttpUtil.doPost(searchUrl+"/search", params);

            System.out.println("-----------");
            System.out.println(searchResultJson);

            TaotaoResult taotaoResult = TaotaoResult.formatToPojo(searchResultJson, SearchResult.class);



            if(taotaoResult.getStatus().equals(SystemConstants.TAOTAO_RESULT_STATUS_OK)) {

                return (SearchResult) taotaoResult.getData();

            }
            return null;


        }

    }

