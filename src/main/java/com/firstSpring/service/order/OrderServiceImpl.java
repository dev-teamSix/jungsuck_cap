package com.firstSpring.service.order;

import com.firstSpring.dao.order.OrderDao;
import com.firstSpring.domain.order.OrderDto;
import com.firstSpring.domain.order.OrderItemDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;

    @Override
    public int getOrderCount(String cust_id) throws Exception {
        return orderDao.countOrder(cust_id);
    }

    @Override
    public int cancel(OrderDto orderDto) throws Exception {
        List<OrderItemDto> listOrderItemDto = orderDao.selectItem(orderDto.getOrd_no());
        for (OrderItemDto orderItemDto : listOrderItemDto) {
            Map map = new HashMap();
            map.put("qty", orderItemDto.getQty());
            map.put("prod_num", orderItemDto.getProd_num());
            orderDao.increaseInvAmnt(map);
        }
        return orderDao.cancelOrder(orderDto);
    }

    @Override
    public int order(OrderDto orderDto, OrderItemDto orderItemDto) throws Exception {
        orderItemDto.setOrd_no(orderDto.getOrd_no());
        Map map = new HashMap();
        map.put("qty", orderItemDto.getQty());
        map.put("prod_num", orderItemDto.getProd_num());
        orderDao.decreaseInvAmnt(map);
        orderDao.insertItem(orderItemDto);
        return orderDao.insertOrder(orderDto);
    }

    @Override
    public List<OrderItemDto> getOrderItem(int ord_no) throws Exception {
        return orderDao.selectItem(ord_no);
    }

    @Override
    public List<OrderDto> getOrderPage(Map map) throws Exception {
        return orderDao.selectOrderPage(map);
    }

    @Override
    public List<OrderDto> getOrder(String cust_id) throws Exception {
        return orderDao.selectOrder(cust_id);
    }
}
