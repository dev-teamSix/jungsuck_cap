package com.firstSpring.dao.order;

import com.firstSpring.domain.order.CartDto;
import com.firstSpring.domain.order.CartItemDto;

import java.util.List;
import java.util.Map;

public interface  CartDao {
    int insertCart(CartDto cartDto) throws Exception;

    int insertCartItem(CartItemDto cartItemDto) throws Exception;

    CartDto selectCart(String cust_id) throws Exception;

    List<CartItemDto> selectCartItem(Integer cart_no) throws Exception;

    int selectCartNo(String cust_id) throws Exception;

    int selectQty(Map map) throws Exception;

    int updateQty(CartItemDto cartItemDto) throws Exception;

    int deleteCart(String cust_id) throws Exception;

    int deleteCartItem(Map map) throws Exception;
}
