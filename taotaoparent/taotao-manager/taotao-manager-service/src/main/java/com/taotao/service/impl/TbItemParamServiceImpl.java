package com.taotao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.commons.EasyUIResult;
import com.taotao.mapper.TbItemParamMapper;
import com.taotao.pojo.TbItemParam;
import com.taotao.service.TbItemParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TbItemParamServiceImpl implements TbItemParamService {

    @Autowired
    private TbItemParamMapper tbItemParamMapper;
    @Override
    public EasyUIResult findItemParamList(int page, int rows) {
        PageHelper.startPage(page, rows);

        List<TbItemParam> tbItemParamList = tbItemParamMapper.selectByExampleWithBLOBs(null);

        PageInfo<TbItemParam> tbItemParamPageInfo = new PageInfo<>(tbItemParamList);

        long total = tbItemParamPageInfo.getTotal();

        EasyUIResult result = new EasyUIResult(total,tbItemParamList);

        return result;

    }
}
