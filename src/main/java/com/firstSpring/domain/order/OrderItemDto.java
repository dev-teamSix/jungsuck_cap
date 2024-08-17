package com.firstSpring.domain.order;

import java.time.LocalDateTime;
import java.util.Objects;

public class OrderItemDto {
    private Integer ord_item_no;
    private Integer ord_no;
    private Integer ord_no_return;
    private Integer prod_num;
    private Integer qty;
    private String prod_name;
    private Integer price;
    private Integer from_cart;
    private String frst_reg_dt;
    private String frst_reg_id;
    private String last_mod_dt;
    private String last_mod_id;

    public OrderItemDto() {
        this.from_cart = 0;
    }
    public OrderItemDto(Integer ord_item_no, Integer ord_no, Integer prod_num, Integer qty, String prod_name, Integer price, Integer from_cart, String frst_reg_dt, String frst_reg_id, String last_mod_dt, String last_mod_id) {
        this.ord_item_no = ord_item_no;
        this.ord_no = ord_no;
        this.prod_num = prod_num;
        this.qty = qty;
        this.prod_name = prod_name;
        this.price = price;
        this.from_cart = from_cart;
        this.frst_reg_dt = frst_reg_dt;
        this.frst_reg_id = frst_reg_id;
        this.last_mod_dt = last_mod_dt;
        this.last_mod_id = last_mod_id;
    }

    public Integer getOrd_item_no() {
        return ord_item_no;
    }

    public void setOrd_item_no(Integer ord_item_no) {
        this.ord_item_no = ord_item_no;
    }

    public Integer getOrd_no() {
        return ord_no;
    }

    public void setOrd_no(Integer ord_no) {
        this.ord_no = ord_no;
    }

    public Integer getProd_num() {
        return prod_num;
    }

    public void setProd_num(Integer prod_num) {
        this.prod_num = prod_num;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public String getProd_name() {
        return prod_name;
    }

    public void setProd_name(String prod_name) {
        this.prod_name = prod_name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getFrst_reg_dt() {
        return frst_reg_dt;
    }

    public void setFrst_reg_dt(String frst_reg_dt) {
        this.frst_reg_dt = frst_reg_dt;
    }

    public String getFrst_reg_id() {
        return frst_reg_id;
    }

    public void setFrst_reg_id(String frst_reg_id) {
        this.frst_reg_id = frst_reg_id;
    }

    public String getLast_mod_dt() {
        return last_mod_dt;
    }

    public void setLast_mod_dt(String last_mod_dt) {
        this.last_mod_dt = last_mod_dt;
    }

    public String getLast_mod_id() {
        return last_mod_id;
    }

    public void setLast_mod_id(String last_mod_id) {
        this.last_mod_id = last_mod_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItemDto that = (OrderItemDto) o;
        return Objects.equals(ord_item_no, that.ord_item_no) && Objects.equals(ord_no, that.ord_no) && Objects.equals(prod_num, that.prod_num) && Objects.equals(qty, that.qty) && Objects.equals(prod_name, that.prod_name) && Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ord_item_no, ord_no, prod_num, qty, prod_name, price);
    }

    @Override
    public String toString() {
        return "OrderItemDto{" +
                "ord_item_no=" + ord_item_no +
                ", ord_no=" + ord_no +
                ", prod_num='" + prod_num + '\'' +
                ", qty=" + qty +
                ", prod_name='" + prod_name + '\'' +
                ", price=" + price +
                ", frst_reg_dt=" + frst_reg_dt +
                ", frst_reg_id='" + frst_reg_id + '\'' +
                ", last_mod_dt=" + last_mod_dt +
                ", last_mod_id='" + last_mod_id + '\'' +
                '}';
    }

    public Integer getOrd_no_return() {
        return ord_no_return;
    }

    public void setOrd_no_return(Integer ord_no_return) {
        this.ord_no_return = ord_no_return;
    }

    public Integer getFrom_cart() {
        return from_cart;
    }

    public void setFrom_cart(Integer from_cart) {
        this.from_cart = from_cart;
    }
}
