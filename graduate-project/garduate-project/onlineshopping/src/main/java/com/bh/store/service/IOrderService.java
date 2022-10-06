package com.bh.store.service;

import com.bh.store.entity.Order;
import com.bh.store.entity.OrderItem;

import java.util.List;

public interface IOrderService {
    Order create(Integer aid,Integer uid,String username,Integer[] cids);

    List<Order> findOrdersByUid(Integer uid);

    List<OrderItem> findOrderItemsByOid(Integer oid);
}
