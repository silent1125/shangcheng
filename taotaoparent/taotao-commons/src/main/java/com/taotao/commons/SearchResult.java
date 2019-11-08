package com.taotao.commons;

import java.util.List;

public class SearchResult {
   private List ItemList;
   private Long PageNum;
   private Long TotalPages;

    public List getItemList() {
        return ItemList;
    }

    public void setItemList(List itemList) {
        ItemList = itemList;
    }

    public Long getPageNum() {
        return PageNum;
    }

    public void setPageNum(Long pageNum) {
        PageNum = pageNum;
    }

    public Long getTotalPages() {
        return TotalPages;
    }

    public void setTotalPages(Long totalPages) {
        TotalPages = totalPages;
    }
}
