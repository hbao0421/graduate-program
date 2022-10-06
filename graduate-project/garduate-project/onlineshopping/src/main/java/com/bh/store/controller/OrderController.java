package com.bh.store.controller;

import com.bh.store.entity.Order;
import com.bh.store.entity.OrderItem;
import com.bh.store.service.IOrderService;
import com.bh.store.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("orders")
public class OrderController extends BaseController{
    @Autowired
    private IOrderService orderService;

    @RequestMapping("create")
    public JsonResult<Order> create(Integer aid, Integer[] cids, HttpSession session){
        Order data = orderService.create(aid, getuidFromSession(session), getUsernameFromSession(session), cids);
        return new JsonResult<>(OK,data);
    }

    @RequestMapping("get_orders")
    public JsonResult<List<Order>> findOrdersByUid(HttpSession session){
        List<Order> data = orderService.findOrdersByUid(getuidFromSession(session));
        return new JsonResult<>(OK,data);
    }

    @RequestMapping("get_orderItems")
    public JsonResult<List<OrderItem>> findOrderItemsByOid(Integer oid){
        List<OrderItem> data = orderService.findOrderItemsByOid(oid);
        return new JsonResult<>(OK,data);
    }
}
