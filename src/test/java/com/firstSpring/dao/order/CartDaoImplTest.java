package com.firstSpring.dao.order;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class CartDaoImplTest {
    @Autowired
    CartDao cartDao;

    @Test
    public void insertCart() {

    }

    @Test
    public void insertCartItem() {
    }

    @Test
    public void selectCart() {
    }

    @Test
    public void selectCartItem() {
    }

    @Test
    public void selectCartNo() throws Exception {
        assertTrue(cartDao != null);
        System.out.println("cartDao = " + cartDao);
        Integer cart_no = cartDao.selectCartNo("asdf");
        System.out.println("cart_no = " + cart_no);
        assertTrue(cart_no.equals(1));
    }

    @Test
    public void selectQty() {

    }

    @Test
    public void updateQty() {
    }

    @Test
    public void deleteCart() {
    }

    @Test
    public void deleteCartItem() {
    }
}