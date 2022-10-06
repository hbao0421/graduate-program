package com.bh.store.service;

import com.bh.store.entity.Order;
import com.bh.store.entity.OrderItem;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderServiceTests {
    @Autowired
    private IOrderService orderService ;
    @Test
    public void create(){
        Integer[] cids = {1,2,3,4};
        Order order = orderService.create(9, 19, "admin", cids);
        System.out.println(order);
    }
    @Test
    public void findOrdersByUid(){
        List<Order> orders = orderService.findOrdersByUid(19);
        for (Order order:orders){
            System.out.println(order);
        }
    }
    @Test
    public void findOrderItemsByOid(){
        for (OrderItem orderItem : orderService.findOrderItemsByOid(7)) {
            System.out.println(orderItem);
        }
    }
}
