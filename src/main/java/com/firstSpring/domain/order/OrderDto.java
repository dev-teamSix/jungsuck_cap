package com.firstSpring.domain.order;

import java.time.LocalDateTime;
import java.util.Objects;

public class OrderDto {
    private Integer ord_no;
    private Integer ord_no_return;
    private String cust_id;
    private String ord_st_cd;
    private String ord_dt;
    private String cnl_dt;
    private String frst_reg_dt;
    private String frst_reg_id;
    private String last_mod_dt;
    private String last_mod_id;

    public OrderDto() {}
    public OrderDto(Integer ord_no, String cust_id, String ord_st_cd, String ord_dt, String cnl_dt, String frst_reg_dt, String frst_reg_id, String last_mod_dt, String last_mod_id) {
        this.ord_no = ord_no;
        this.cust_id = cust_id;
        this.ord_st_cd = ord_st_cd;
        this.ord_dt = ord_dt;
        this.cnl_dt = cnl_dt;
        this.frst_reg_dt = frst_reg_dt;
        this.frst_reg_id = frst_reg_id;
        this.last_mod_dt = last_mod_dt;
        this.last_mod_id = last_mod_id;
    }

    public Integer getOrd_no() {
        return ord_no;
    }

    public void setOrd_no(Integer ord_no) {
        this.ord_no = ord_no;
    }

    public String getCust_id() {
        return cust_id;
    }

    public void setCust_id(String cust_id) {
        this.cust_id = cust_id;
    }

    public String getOrd_st_cd() {
        return ord_st_cd;
    }

    public void setOrd_st_cd(String ord_st_cd) {
        this.ord_st_cd = ord_st_cd;
    }

    public String getOrd_dt() {
        return ord_dt;
    }

    public void setOrd_dt(String ord_dt) {
        this.ord_dt = ord_dt;
    }

    public String getCnl_dt() {
        return cnl_dt;
    }

    public void setCnl_dt(String cnl_dt) {
        this.cnl_dt = cnl_dt;
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
        OrderDto orderDto = (OrderDto) o;
        return ord_no == orderDto.ord_no && Objects.equals(cust_id, orderDto.cust_id) && Objects.equals(ord_st_cd, orderDto.ord_st_cd) && Objects.equals(ord_dt, orderDto.ord_dt) && Objects.equals(cnl_dt, orderDto.cnl_dt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ord_no, cust_id, ord_st_cd, ord_dt, cnl_dt);
    }

    @Override
    public String toString() {
        return "OrderDto{" +
                "ord_no=" + ord_no +
                ", cust_id='" + cust_id + '\'' +
                ", ord_st_cd='" + ord_st_cd + '\'' +
                ", ord_dt=" + ord_dt +
                ", cnl_dt=" + cnl_dt +
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
}
