package com.taotao.service;

import com.taotao.commons.EUTreeNode;

import java.util.List;

public interface TbItemCatService {
    public List<EUTreeNode> itemCatList(long parentId);
}
