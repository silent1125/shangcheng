package com.taotao.service;

import com.taotao.commons.SearchResult;

public interface SearchService {
    SearchResult search(String keywords, Integer pageNum, Integer pageSize);
}
