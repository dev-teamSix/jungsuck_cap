package com.firstSpring.domain.order;

import com.firstSpring.entity.Item;

import java.util.ArrayList;
import java.util.List;

public class ItemFormDto {
    private int id;
    private String itemNm;
    private int price;
    private String itemDetail;
    private int stockNumber;
    private String itemSellStatus;
    private List<ItemImgDto> itemImgDtoList = new ArrayList<>();
    private List<Integer> itemImgIds = new ArrayList<>();

    public Item createItem() {
        Item item = new Item();
        item.setId(id);
        item.setItemNm(itemNm);
        item.setPrice(price);
        item.setStockNumber(stockNumber);
        item.setItemDetail(itemDetail);
        item.setItemSellStaus(itemSellStatus);
        return item;
    }

    public static ItemFormDto of(Item item) {
        ItemFormDto itemFormDto = new ItemFormDto();
        itemFormDto.setId(item.getId());
        itemFormDto.setItemNm(item.getItemNm());
        itemFormDto.setPrice(item.getPrice());
        itemFormDto.setStockNumber(item.getStockNumber());
        itemFormDto.setItemDetail(item.getItemDetail());
        itemFormDto.setItemSellStatus(item.getItemSellStaus());
        return itemFormDto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItemNm() {
        return itemNm;
    }

    public void setItemNm(String itemNm) {
        this.itemNm = itemNm;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getItemDetail() {
        return itemDetail;
    }

    public void setItemDetail(String itemDetail) {
        this.itemDetail = itemDetail;
    }

    public int getStockNumber() {
        return stockNumber;
    }

    public void setStockNumber(int stockNumber) {
        this.stockNumber = stockNumber;
    }

    public String getItemSellStatus() {
        return itemSellStatus;
    }

    public void setItemSellStatus(String itemSellStatus) {
        this.itemSellStatus = itemSellStatus;
    }

    public List<ItemImgDto> getItemImgDtoList() {
        return itemImgDtoList;
    }

    public void setItemImgDtoList(List<ItemImgDto> itemImgDtoList) {
        this.itemImgDtoList = itemImgDtoList;
    }

    public List<Integer> getItemImgIds() {
        return itemImgIds;
    }

    public void setItemImgIds(List<Integer> itemImgIds) {
        this.itemImgIds = itemImgIds;
    }
}
