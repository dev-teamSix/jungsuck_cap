package com.firstSpring.domain.order;

import com.firstSpring.entity.ItemImg;

public class ItemImgDto {

    private Long id;

    private String imgName;

    private String oriImgName;

    private String imgUrl;

    private String repImgYn;

    public static ItemImgDto of(ItemImg itemImg) {
        ItemImgDto itemImgDto = new ItemImgDto();
        itemImgDto.setId(itemImg.getId());
        itemImgDto.setImgName(itemImg.getImgName());
        itemImgDto.setOriImgName(itemImg.getOriImgName());
        itemImgDto.setImgUrl(itemImg.getImgUrl());
        itemImgDto.setRepImgYn(itemImg.getRepimgYn());
        return itemImgDto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public String getOriImgName() {
        return oriImgName;
    }

    public void setOriImgName(String oriImgName) {
        this.oriImgName = oriImgName;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getRepImgYn() {
        return repImgYn;
    }

    public void setRepImgYn(String repImgYn) {
        this.repImgYn = repImgYn;
    }
}
