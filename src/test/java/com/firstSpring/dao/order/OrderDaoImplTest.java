package com.firstSpring.dao.order;

import com.firstSpring.domain.order.OrderDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TestTransaction;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class OrderDaoImplTest {
    @Autowired
    OrderDao orderDao;

    @Test
    public void increaseInvAmnt() throws Exception {

    }

    @Test
    public void decreaseInvAmt() {
    }

    @Test
    public void cancelOrder() {
    }

    @Test
    public void insertItem() {
    }

    @Test
    public void insertOrder() throws Exception {

    }

    @Test
    public void selectProdName() throws Exception {
        assertTrue(orderDao != null);
        System.out.println("orderDao = " + orderDao);
        String prodName = orderDao.selectProdName("P001");
        System.out.println("prodName = " + prodName);
    }

    @Test
    public void selectPrice() {
    }

    @Test
    public void selectItem() {
    }

    @Test
    public void selectOrder() throws Exception {
        OrderDto orderDto = orderDao.selectOrder("cat").get(0);
        System.out.println("orderDto = " + orderDto);
        assertTrue(orderDto.getOrd_no().equals(1));
    }

    @Test
    public void selectOrderPage() {
    }

    @Test
    public void countOrder() {
    }
}