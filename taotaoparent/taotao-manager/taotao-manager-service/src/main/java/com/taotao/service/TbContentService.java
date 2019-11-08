package com.taotao.service;

import com.taotao.commons.EasyUIResult;
import com.taotao.commons.TaotaoResult;
import com.taotao.pojo.TbContent;

import java.util.List;

public interface TbContentService {

    public List<TbContent> listAll();

    //查询内容列表
    EasyUIResult getContentList(long catId, Integer page, Integer rows);

    //新增内容
    TaotaoResult addContent(TbContent tbContent);

}
