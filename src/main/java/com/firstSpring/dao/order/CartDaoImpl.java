package com.firstSpring.dao.order;

import com.firstSpring.domain.order.CartDto;
import com.firstSpring.domain.order.CartItemDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class  CartDaoImpl implements CartDao {
    @Autowired
    private SqlSession session;
    private static String namespace="com.firstSpring.dao.order.CartMapper.";

    @Override
    public int insertCart(CartDto cartDto) throws Exception {
        return session.update(namespace+"insertCart", cartDto);
    }

    @Override
    public int insertCartItem(CartItemDto cartItemDto) throws Exception {
        return session.update(namespace+"insertCartItem", cartItemDto);
    }

    @Override
    public CartDto selectCart(String cust_id) throws Exception {
        return session.selectOne(namespace+"selectCart", cust_id);
    }

    @Override
    public List<CartItemDto> selectCartItem(Integer cart_no) throws Exception {
        return session.selectList(namespace+"selectCartItem", cart_no);
    }

    @Override
    public int selectCartNo(String cust_id) throws Exception {
        return session.selectOne(namespace+"selectCartNo", cust_id);
    }

    @Override
    public int selectQty(Map map) throws Exception {
        return session.selectOne(namespace+"selectQty", map);
    }

    @Override
    public int updateQty(CartItemDto cartItemDto) throws Exception {
        return session.update(namespace+"updateQty", cartItemDto);
    }

    @Override
    public int deleteCart(String cust_id) throws Exception {
        return session.delete(namespace+"deleteCart", cust_id);
    }

    @Override
    public int deleteCartItem(Map map) throws Exception {
        return session.delete(namespace+"deleteCartItem", map);
    }

}
