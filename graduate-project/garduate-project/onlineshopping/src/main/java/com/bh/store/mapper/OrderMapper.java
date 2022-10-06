package com.bh.store.mapper;

import com.bh.store.entity.Order;
import com.bh.store.entity.OrderItem;

import java.util.List;

public interface OrderMapper {
    /**
     * 插入订单数据
     * @param order 订单数据
     * @return 影响行数
     */
    Integer insertOrder(Order order);

    /**
     * 插入订单数据项
     * @param orderItem 订单数据项
     * @return 受到影响行数
     */
    Integer insertOrderItem(OrderItem orderItem);

    List<Order> findOrdersByUid(Integer uid);

    List<OrderItem> findOrderItemsByOid(Integer oid);
}
