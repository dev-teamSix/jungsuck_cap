package com.firstSpring.domain.order;

import java.util.List;

public class ActionCartOrderDto {

    private Integer cart_item_no;

    private List<CartItemDto> cartItemDtoList;

    public Integer getCart_item_no() {
        return cart_item_no;
    }

    public void setCart_item_no(Integer cart_item_no) {
        this.cart_item_no = cart_item_no;
    }

    public List<CartItemDto> getCartItemDtoList() {
        return cartItemDtoList;
    }

    public void setCartItemDtoList(List<CartItemDto> cartItemDtoList) {
        this.cartItemDtoList = cartItemDtoList;
    }

}
