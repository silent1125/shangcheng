package com.taotao.service.impl;

import com.taotao.commons.EUTreeNode;
import com.taotao.commons.TaotaoResult;
import com.taotao.mapper.TbContentCategoryMapper;
import com.taotao.pojo.TbContentCategory;
import com.taotao.pojo.TbContentCategoryExample;
import com.taotao.service.TbContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
public class TbContentCategoryServiceImpl implements TbContentCategoryService {
    @Autowired
    private TbContentCategoryMapper tbContentCategoryMapper;
    @Override
    public List<EUTreeNode> selectByExample(long parentId) {

        //根据parentId查询内容分类列表
        TbContentCategoryExample example = new TbContentCategoryExample();
        TbContentCategoryExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        List<TbContentCategory> list = tbContentCategoryMapper.selectByExample(example);
        List<EUTreeNode> resultList = new ArrayList<>();
        for (TbContentCategory tbContentCategory : list) {
            EUTreeNode node = new EUTreeNode();
            node.setId(tbContentCategory.getId());
            node.setText(tbContentCategory.getName());
            //判断是否是父节点
            if (tbContentCategory.getIsParent()) {
                node.setState("closed");
            } else {
                node.setState("open");
            }
            resultList.add(node);
        }
        return resultList;

    }

    @Override
    public TaotaoResult addNode(long parentId, String name) {
        Date date = new Date();
        //添加一个新节点
        //创建一个节点对象
        TbContentCategory node = new TbContentCategory();
        node.setName(name);
        node.setParentId(parentId);
        node.setIsParent(false);
        node.setCreated(date);
        node.setUpdated(date);
        node.setSortOrder(1);
        //状态。可选值:1(正常),2(删除)
        node.setStatus(1);
        //插入新节点。需要返回主键
        tbContentCategoryMapper.insert(node);
        //判断如果父节点的isParent不是true修改为true
        //取父节点的内容
        TbContentCategory parentNode = tbContentCategoryMapper.selectByPrimaryKey(parentId);
        if (!parentNode.getIsParent()) {
            parentNode.setIsParent(true);
            tbContentCategoryMapper.updateByPrimaryKey(parentNode);
        }
        //把新节点返回
        return TaotaoResult.ok(node);
    }

    @Override
    public TaotaoResult deleteNode(Long id) {
        deleteAll(id);
        return TaotaoResult.ok();
    }

    @Override
    public List<TbContentCategory> getChildrenNodeList(Long id) {

        //如果传入节点为父节点，将其所有子节点查询出来，子节点可能还有子节点，递归查询出来
        TbContentCategoryExample categoryExample = new TbContentCategoryExample();
        TbContentCategoryExample.Criteria criteria = categoryExample.createCriteria().andParentIdEqualTo(id);
        //返回所有节点
        return tbContentCategoryMapper.selectByExample(categoryExample);
    }

    @Override
    public void deleteAll(Long id) {
        //获取要删除的内容节点名称
        TbContentCategory tbContentCategory = tbContentCategoryMapper.selectByPrimaryKey(id);
        //判断是否是父级节点
        if (tbContentCategory.getIsParent()){
            //如果是父级节点，获得它的子节点
            List<TbContentCategory> list = getChildrenNodeList(id);
            //删除所有的子节点
            for (TbContentCategory tb : list){
                deleteAll(tb.getId());
            }
        }

        //判断父级节点下面是否还有其他子节点
        if (getChildrenNodeList(tbContentCategory.getParentId()).size()==1){
            //若是没有子节点，说明该节点就是叶子节点，将其标记为叶子节点
            TbContentCategory contentCategory =
                    tbContentCategoryMapper.selectByPrimaryKey(tbContentCategory.getParentId());
            contentCategory.setIsParent(false);
            tbContentCategoryMapper.updateByPrimaryKey(contentCategory);
        }
        //删除本节点
        tbContentCategoryMapper.deleteByPrimaryKey(id);

    }





}
