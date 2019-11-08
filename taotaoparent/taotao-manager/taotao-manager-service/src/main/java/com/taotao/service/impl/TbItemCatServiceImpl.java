package com.taotao.service.impl;

import com.taotao.commons.EUTreeNode;
import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.service.TbItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class TbItemCatServiceImpl implements TbItemCatService {
    @Autowired
    private TbItemCatMapper tbItemCatMapper;

    @Override
    public List<EUTreeNode> itemCatList(long parentId) {
        TbItemCatExample itemCat = new TbItemCatExample();
        //itemCat.createCriteria() 创建查询对象
        //andParentId
        itemCat.createCriteria().andParentIdEqualTo(parentId);
        //所有的分类
        List<TbItemCat> list = tbItemCatMapper.selectByExample(itemCat);

        List<EUTreeNode> treeNodeList = new ArrayList<>();

        for (TbItemCat cat : list){
            EUTreeNode node = new EUTreeNode();
            node.setId(cat.getId());
            node.setText(cat.getName());
            node.setState(cat.getIsParent() ? "closed" : "open");
            treeNodeList.add(node);
        }
        return treeNodeList;
    }
}
