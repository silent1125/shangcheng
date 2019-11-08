package com.taotao.mapper;

import com.taotao.pojo.SearchItem;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
public interface TbItemMapper {
    long countByExample(TbItemExample example);

    int deleteByExample(TbItemExample example);

    int deleteByPrimaryKey(@Param("ids") List ids);

    int insert(TbItem record);

    int insertSelective(TbItem record);

    List<TbItem> selectByExample(TbItemExample example);

    TbItem selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TbItem record, @Param("example") TbItemExample example);

    int updateByExample(@Param("record") TbItem record, @Param("example") TbItemExample example);

    int updateByPrimaryKeySelective(TbItem record);

    int updateByPrimaryKey(TbItem record);


    // 商品状态修改(正常,下架,删除)
    int updateItemStatusPrimaryKey( @Param("ids") List ids);
    //上架
    int updateItemStatusPrimaryKey2( @Param("ids") List ids);

    //搜索
    List<SearchItem> selectAllSearchItem();

}