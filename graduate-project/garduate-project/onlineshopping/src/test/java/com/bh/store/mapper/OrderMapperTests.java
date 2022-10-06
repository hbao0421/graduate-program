package com.bh.store.mapper;

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
public class OrderMapperTests {
    @Autowired
    private OrderMapper orderMapper ;
    @Test
    public void insertOrder(){
        Order order= new Order();
        order.setUid(19);
        order.setRecvName("admin");
        order.setRecvPhone("17321420421");
        orderMapper.insertOrder(order);
    }
    @Test
    public void  insertOrderItem(){
        OrderItem orderItem = new OrderItem();
        orderItem.setNum(1);
        orderItem.setOid(1);
        orderMapper.insertOrderItem(orderItem);
    }

    @Test
    public void findOrdersByUid(){
        List<Order> orders = orderMapper.findOrdersByUid(19);
        for (Order o:orders){
            System.out.println(orders);
        }
    }
    @Test
    public void findOrderItemsByOid(){
        for (OrderItem orderItem : orderMapper.findOrderItemsByOid(8)) {
            System.out.println(orderItem);
        }
    }
}
