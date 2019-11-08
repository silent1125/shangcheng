package com.taotao.service;

import com.taotao.commons.EUTreeNode;
import com.taotao.commons.EasyUIResult;
import com.taotao.commons.TaotaoResult;
import com.taotao.pojo.TbContentCategory;
import com.taotao.pojo.TbContentCategoryExample;

import java.util.List;

public interface TbContentCategoryService {
    //查询内容分类树形组件
    List<EUTreeNode> selectByExample(long parentId);

    //添加内容
    TaotaoResult addNode(long parentId, String name);

    //删除内容节点
    TaotaoResult deleteNode(Long id);

    //获得节点的子节点
    List<TbContentCategory> getChildrenNodeList(Long id);

    //删除节点以及子节点
    void deleteAll(Long id);


}
