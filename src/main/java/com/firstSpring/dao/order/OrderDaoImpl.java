package com.firstSpring.dao.order;

import com.firstSpring.domain.order.OrderDto;
import com.firstSpring.domain.order.OrderItemDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class OrderDaoImpl implements OrderDao {
    @Autowired
    private SqlSession session;
    private static String namespace = "com.firstSpring.dao.order.OrderMapper.";

    @Override
    public int increaseInvAmnt(Map map) throws Exception {
        return session.update(namespace+"increaseInvAmnt", map);
    }

    @Override
    public int decreaseInvAmnt(Map map) throws Exception {
        return session.update(namespace+"decreaseInvAmnt", map);
    }

    @Override
    public int cancelOrder(OrderDto orderDto) throws Exception {
        return session.update(namespace+"cancelOrder", orderDto);
    }

    @Override
    public int insertItem(OrderItemDto dto) throws Exception {
        return session.insert(namespace+"insertItem", dto);
    }

    @Override
    public int insertOrder(OrderDto dto) throws Exception {
        return session.insert(namespace+"insertOrder", dto);
    }

    @Override
    public String selectProdName(String prod_num) throws Exception {
        return session.selectOne(namespace+"selectProdName", prod_num);
    }

    @Override
    public int selectPrice(String prod_num) throws Exception {
        return session.selectOne(namespace+"selectPrice", prod_num);
    }

    @Override
    public List<OrderItemDto> selectItem(Integer ord_no) throws Exception {
        return session.selectList(namespace+"selectItem", ord_no);
    }

    @Override
    public List<OrderDto> selectOrder(String cust_id) throws Exception {
        return session.selectList(namespace+"selectOrder", cust_id);
    }

    @Override
    public List<OrderDto> selectOrderPage(Map map) {
        return session.selectList(namespace+"selectOrderPage", map);
    }

    @Override
    public int countOrder(String cust_id) throws Exception {
        return session.selectOne(namespace+"countOrder", cust_id);
    }
}