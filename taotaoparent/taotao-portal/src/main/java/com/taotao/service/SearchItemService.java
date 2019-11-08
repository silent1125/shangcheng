package com.taotao.service;

import com.taotao.commons.SearchResult;

public interface SearchItemService {
    SearchResult search(String keywords, Integer pageNum, Integer pageSize);
}
