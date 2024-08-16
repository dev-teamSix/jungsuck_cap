package com.firstSpring.service.order;

import com.firstSpring.domain.order.CartItemDto;

import java.util.List;
import java.util.Map;

public interface CartService {
    int insertCart(String cust_id) throws Exception;

    int insertCartItem(String cust_id, CartItemDto cartItemDto) throws Exception;

    int getCartNo(String cust_id) throws Exception;

    List<CartItemDto> getCartItem(Integer cart_no) throws Exception;

    int updateQty(String cust_id, CartItemDto cartItemDto) throws Exception;

    int deleteCart(String cust_id) throws Exception;

    int deleteCartItem(Map map) throws Exception;
}
