package com.taotao.service;

import com.taotao.commons.EasyUIResult;

public interface TbItemParamService {

    //按照分页信息查询数据
    public EasyUIResult findItemParamList(int page, int rows);
}
