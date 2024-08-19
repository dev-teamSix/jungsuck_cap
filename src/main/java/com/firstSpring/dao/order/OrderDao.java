package com.firstSpring.dao.order;

import com.firstSpring.domain.order.OrderDto;
import com.firstSpring.domain.order.OrderItemDto;

import java.util.List;
import java.util.Map;

public interface OrderDao {
    int increaseInvAmnt(Map map) throws Exception;

    int decreaseInvAmnt(Map map) throws Exception;

    int cancelOrder(OrderDto orderDto) throws Exception;

    int insertItem(OrderItemDto dto) throws Exception;

    int insertItemReturn(Map map) throws Exception;

    int insertOrder(OrderDto dto) throws Exception;

    String selectProdName(Integer prod_no) throws Exception;

    int selectPrice(String prod_no) throws Exception;

    List<OrderItemDto> selectItem(Integer ord_no) throws Exception;

    List<OrderDto> selectOrder(String cust_id) throws Exception;

    List<OrderDto> selectOrderPage(Map map);

    int countOrder(String cust_id) throws Exception;

    int maxOrdNo() throws Exception;

    int maxOrdItemNo() throws Exception;
}
