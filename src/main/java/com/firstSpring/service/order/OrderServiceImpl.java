package com.firstSpring.service.order;

import com.firstSpring.dao.order.OrderDao;
import com.firstSpring.domain.order.OrderDto;
import com.firstSpring.domain.order.OrderItemDto;
//import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.time.LocalDateTime.now;

@Service
public class OrderServiceImpl implements OrderService {
    private final PlatformTransactionManager transactionManager;

    public OrderServiceImpl(PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    @Autowired
    private OrderDao orderDao;

    @Override
    public int getOrderCount(String cust_id) throws Exception {
        return orderDao.countOrder(cust_id);
    }

    @Override
//    @Transactional(rollbackFor = {Exception.class})
    public int cancel(OrderDto orderDto) throws Exception {
        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());

        try {
            List<OrderItemDto> listOrderItemDto = orderDao.selectItem(orderDto.getOrd_no());
            for (OrderItemDto orderItemDto : listOrderItemDto) {
                Map map = new HashMap();
                map.put("qty", orderItemDto.getQty());
                map.put("prod_no", orderItemDto.getProd_no());
                map.put("col_no", orderItemDto.getCol_no());
                orderDao.increaseInvAmnt(map);
            }
//            throw new Exception("test");
            orderDao.cancelOrder(orderDto);

            transactionManager.commit(status);
            return 1;
        } catch (Exception e) {
            // e.printStackTrace();
            transactionManager.rollback(status);
            throw e;
        }
    }

    @Override
//    @Transactional(rollbackFor = {Exception.class})
    public int order(OrderItemDto orderItemDto, String cust_id) throws Exception {
        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());

        try {
            if(orderItemDto.getFrom_cart() > 0) {
                orderItemDto.setOrd_no(orderItemDto.getFrom_cart());
                orderItemDto.setFrst_reg_dt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                orderItemDto.setFrst_reg_id(cust_id);
                orderItemDto.setLast_mod_dt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                orderItemDto.setLast_mod_id(cust_id);

                orderDao.insertItem(orderItemDto);

                Map map = new HashMap();
                map.put("qty", orderItemDto.getQty());
                map.put("prod_no", orderItemDto.getProd_no());
                map.put("col_no", orderItemDto.getCol_no());
                orderDao.decreaseInvAmnt(map);
            } else {
                OrderDto orderDto = new OrderDto();
                orderDto.setCust_id(cust_id);
                orderDto.setOrd_st_cd("O");
                orderDto.setOrd_dt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                orderDto.setCnl_dt(null);
                orderDto.setFrst_reg_dt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                orderDto.setFrst_reg_id(cust_id);
                orderDto.setLast_mod_dt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                orderDto.setLast_mod_id(cust_id);

                orderItemDto.setFrst_reg_dt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                orderItemDto.setFrst_reg_id(cust_id);
                orderItemDto.setLast_mod_dt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                orderItemDto.setLast_mod_id(cust_id);

                orderDao.insertOrder(orderDto);
//            throw new Exception("test");
                Map<String, Object> map1 = new HashMap<>();
                map1.put("orderItemDto", orderItemDto);
                map1.put("orderDto", orderDto);
                orderDao.insertItemReturn(map1);

                Map map2 = new HashMap();
                map2.put("qty", orderItemDto.getQty());
                map2.put("prod_no", orderItemDto.getProd_no());
                map2.put("col_no", orderItemDto.getCol_no());
                orderDao.decreaseInvAmnt(map2);
            }

            transactionManager.commit(status);
            return 1;
        } catch (Exception e) {
//            e.printStackTrace();
            transactionManager.rollback(status);
            throw e;
        }
    }

    @Override
    public List<OrderDto> getOrder(String cust_id) throws Exception {
        return orderDao.selectOrder(cust_id);
    }

    @Override
    public List<OrderDto> getOrderPage(Map map) throws Exception {
        return orderDao.selectOrderPage(map);
    }

    @Override
    public List<OrderItemDto> getOrderItem(int ord_no) throws Exception {
        return orderDao.selectItem(ord_no);
    }

    @Override
    public int maxOrdNo() throws Exception {
        return orderDao.maxOrdNo();
    }

    @Override
    public int maxOrdItemNo() throws Exception {
        return orderDao.maxOrdItemNo();
    }
}
