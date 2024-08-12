package com.firstSpring.entity;

import com.firstSpring.domain.order.ItemFormDto;
import com.firstSpring.exception.OutOfStockException;

public class Item extends BaseEntity {
    private int id;
    private String itemNm;
    private int price;
    private int stockNumber;
    private String itemDetail;
    private String itemSellStaus;

    public void updateItem(ItemFormDto itemFormDto) {
        this.itemNm = itemFormDto.getItemNm();
        this.price = itemFormDto.getPrice();
        this.stockNumber = itemFormDto.getStockNumber();
        this.itemDetail = itemFormDto.getItemDetail();
        this.itemSellStaus = itemFormDto.getItemSellStatus();
    }

    public void removeStock(int stockNumber) {
        int restStock = this.stockNumber - stockNumber;
        if (restStock < 0) {
            throw new OutOfStockException("상품의 재고가 부족합니다. (현재 재고 수량: " + this.stockNumber + ")");
        }
        this.stockNumber = restStock;
    }

    public void addStock(int stockNumber) {
        this.stockNumber += stockNumber;
    }

    public int getId() {
        return this.id;
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

    public int getStockNumber() {
        return stockNumber;
    }

    public void setStockNumber(int stockNumber) {
        this.stockNumber = stockNumber;
    }

    public String getItemDetail() {
        return itemDetail;
    }

    public void setItemDetail(String itemDetail) {
        this.itemDetail = itemDetail;
    }

    public String getItemSellStaus() {
        return itemSellStaus;
    }

    public void setItemSellStaus(String itemSellStaus) {
        this.itemSellStaus = itemSellStaus;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", itemNm='" + itemNm + '\'' +
                ", price=" + price +
                ", stockNumber=" + stockNumber +
                ", itemDetail='" + itemDetail + '\'' +
                ", itemSellStaus='" + itemSellStaus + '\'' +
                '}';
    }
}
