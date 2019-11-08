package com.taotao.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.commons.EasyUIResult;
import com.taotao.commons.TaotaoResult;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentCategory;
import com.taotao.pojo.TbContentCategoryExample;
import com.taotao.pojo.TbContentExample;
import com.taotao.service.TbContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TbContentServiceImpl implements TbContentService {

    @Autowired
    private TbContentMapper tbContentMapper;

    @Override
    public List<TbContent> listAll() {
        return tbContentMapper.selectByExample(null);
    }

    //查找内容列表
    @Override
    public EasyUIResult getContentList(long catId, Integer page, Integer rows) {
        //根据category id查询内容列表
        TbContentExample contentExample = new TbContentExample();
        TbContentExample.Criteria criteria = contentExample.createCriteria();
        criteria.andCategoryIdEqualTo(catId);
        //分页处理
        PageHelper.startPage(page,rows);
        List<TbContent> list = tbContentMapper.selectByExampleWithBLOBs(contentExample);
        //取分页信息
        PageInfo<TbContent> pageInfo = new PageInfo<>(list);
        EasyUIResult result = new EasyUIResult(pageInfo.getTotal(),list);
        return result;
    }

    @Override
    public TaotaoResult addContent(TbContent tbContent) {
        //保存图片信息
        tbContent.setCreated(new Date());
        tbContent.setUpdated(new Date());
        int n = tbContentMapper.insertSelective(tbContent);
       return TaotaoResult.ok();

    }
}
