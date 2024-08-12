package com.firstSpring.domain.order;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OrderHistDto {

    public OrderHistDto() {}
    public OrderHistDto(int orderId, LocalDateTime orderDate, String orderStatus) {
        this.orderId = orderId;
        this.orderDate = orderDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        this.orderStatus = orderStatus;
    }

    private int orderId; //주문아이디
    private String orderDate; //주문날짜
    private String orderStatus; //주문 상태

    private List<OrderItemDto> orderItemDtoList = new ArrayList<>();

    //주문 상품리스트
    public void addOrderItemDto(OrderItemDto orderItemDto) {
        orderItemDtoList.add(orderItemDto);
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public List<OrderItemDto> getOrderItemDtoList() {
        return orderItemDtoList;
    }

    public void setOrderItemDtoList(List<OrderItemDto> orderItemDtoList) {
        this.orderItemDtoList = orderItemDtoList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderHistDto that = (OrderHistDto) o;
        return orderId == that.orderId && Objects.equals(orderDate, that.orderDate) && Objects.equals(orderStatus, that.orderStatus) && Objects.equals(orderItemDtoList, that.orderItemDtoList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, orderDate, orderStatus, orderItemDtoList);
    }

    @Override
    public String toString() {
        return "OrderHistDto{" +
                "orderId=" + orderId +
                ", orderDate='" + orderDate + '\'' +
                ", orderStatus='" + orderStatus + '\'' +
                ", orderItemDtoList=" + orderItemDtoList +
                '}';
    }
}
