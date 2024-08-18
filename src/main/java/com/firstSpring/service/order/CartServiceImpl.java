package com.firstSpring.service.order;

import com.firstSpring.dao.order.CartDao;
import com.firstSpring.domain.order.CartDto;
import com.firstSpring.domain.order.CartItemDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import static java.time.LocalDateTime.now;

@Service
public class  CartServiceImpl implements CartService {
    @Autowired
    private CartDao cartDao;

    @Override
    public boolean insertCart(String cust_id) {
        CartDto cartDto = new CartDto();
        cartDto.setCust_id(cust_id);
        cartDto.setFrst_reg_dt(LocalDateTime.parse(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));
        cartDto.setFrst_reg_id(cust_id);
        cartDto.setLast_mod_dt(LocalDateTime.parse(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));
        cartDto.setLast_mod_id(cust_id);

        try {
            cartDao.insertCart(cartDto);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public int insertCartItem(String cust_id, CartItemDto cartItemDto) throws Exception {
        Integer cart_no = cartDao.selectCartNo(cust_id);
        cartItemDto.setCart_no(cart_no);
        cartItemDto.setFrst_reg_dt(now());
        cartItemDto.setFrst_reg_id(cust_id);
        cartItemDto.setLast_mod_dt(now());
        cartItemDto.setLast_mod_id(cust_id);
        return cartDao.insertCartItem(cartItemDto);
    }

    @Override
    public int getCartNo(String cust_id) throws Exception {
        return cartDao.selectCartNo(cust_id);
    }

    @Override
    public List<CartItemDto> getCartItem(Integer cart_no) throws Exception {
        return cartDao.selectCartItem(cart_no);
    }

    @Override
    public int updateQty(String cust_id, CartItemDto cartItemDto) throws Exception {
        Integer cart_no = cartDao.selectCartNo(cust_id);
        cartItemDto.setCart_no(cart_no);
        cartItemDto.setFrst_reg_dt(now());
        cartItemDto.setFrst_reg_id(cust_id);
        cartItemDto.setLast_mod_dt(now());
        cartItemDto.setLast_mod_id(cust_id);
        return cartDao.updateQty(cartItemDto);
    }

    @Override
    public int deleteCart(String cust_id) throws Exception {
        return cartDao.deleteCart(cust_id);
    }

    @Override
    public int deleteCartItem(Map map) throws Exception {
        return cartDao.deleteCartItem(map);
    }
}
