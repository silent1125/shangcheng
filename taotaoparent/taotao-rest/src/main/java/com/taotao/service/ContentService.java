package com.taotao.service;

import com.taotao.commons.TaotaoResult;


public interface ContentService {
    //根据id查找图片链接
    TaotaoResult getContentList(long cid);

}
