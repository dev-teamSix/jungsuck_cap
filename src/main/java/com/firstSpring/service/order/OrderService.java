package com.firstSpring.service.order;

import com.firstSpring.domain.order.OrderDto;
import com.firstSpring.domain.order.OrderItemDto;

import java.util.List;
import java.util.Map;

public interface  OrderService {
    int getOrderCount(String cust_id) throws Exception;

    int cancel(OrderDto orderDto) throws Exception;

    int order(OrderItemDto orderItemDto, String cust_id) throws Exception;

    List<OrderItemDto> getOrderItem(int ord_no) throws Exception;

    List<OrderDto> getOrderPage(Map map) throws Exception;

    List<OrderDto> getOrder(String cust_id) throws Exception;

    int maxOrdNo() throws Exception;

    int maxOrdItemNo() throws Exception;
}
