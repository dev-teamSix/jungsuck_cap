package com.firstSpring.domain.order;

import java.time.LocalDateTime;
import java.util.Objects;

public class CartDto {
    private Integer cart_no;
    private String cust_id;
    private LocalDateTime frst_reg_dt;
    private String frst_reg_id;
    private LocalDateTime last_mod_dt;
    private String last_mod_id;

    public CartDto() {}
    public CartDto(Integer cart_no, String cust_id, LocalDateTime frst_reg_dt, String frst_reg_id, LocalDateTime last_mod_dt, String last_mod_id) {
        this.cart_no = cart_no;
        this.cust_id = cust_id;
        this.frst_reg_dt = frst_reg_dt;
        this.frst_reg_id = frst_reg_id;
        this.last_mod_dt = last_mod_dt;
        this.last_mod_id = last_mod_id;
    }

    public Integer getCart_no() {
        return cart_no;
    }

    public void setCart_no(Integer cart_no) {
        this.cart_no = cart_no;
    }

    public String getCust_id() {
        return cust_id;
    }

    public void setCust_id(String cust_id) {
        this.cust_id = cust_id;
    }

    public LocalDateTime getFrst_reg_dt() {
        return frst_reg_dt;
    }

    public void setFrst_reg_dt(LocalDateTime frst_reg_dt) {
        this.frst_reg_dt = frst_reg_dt;
    }

    public String getFrst_reg_id() {
        return frst_reg_id;
    }

    public void setFrst_reg_id(String frst_reg_id) {
        this.frst_reg_id = frst_reg_id;
    }

    public LocalDateTime getLast_mod_dt() {
        return last_mod_dt;
    }

    public void setLast_mod_dt(LocalDateTime last_mod_dt) {
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
        CartDto cartDto = (CartDto) o;
        return Objects.equals(cart_no, cartDto.cart_no) && Objects.equals(cust_id, cartDto.cust_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cart_no, cust_id);
    }

    @Override
    public String toString() {
        return "CartDto{" +
                "cart_no=" + cart_no +
                ", cust_id='" + cust_id + '\'' +
                ", frst_reg_dt=" + frst_reg_dt +
                ", frst_reg_id='" + frst_reg_id + '\'' +
                ", last_mod_dt=" + last_mod_dt +
                ", last_mod_id='" + last_mod_id + '\'' +
                '}';
    }
}
