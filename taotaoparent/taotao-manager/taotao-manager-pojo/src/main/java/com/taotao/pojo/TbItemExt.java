package com.taotao.pojo;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

public class TbItemExt extends TbItem implements Serializable {
    private String[] images;
    @JsonIgnoreProperties(ignoreUnknown = true)
    public String[] getImages(){
        if (this.getImage() != null && this.getImage()!=""){
            String[] strings = this.getImage().split(",");
            return strings;
        }
        return null;
    }
    public void setImages(String[] images){
        this.images = images;
    }
}
