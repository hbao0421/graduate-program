package com.bh.store.service.impl;

import com.bh.store.entity.Address;
import com.bh.store.entity.Order;
import com.bh.store.entity.OrderItem;
import com.bh.store.entity.Product;
import com.bh.store.mapper.OrderMapper;
import com.bh.store.mapper.ProductMapper;
import com.bh.store.service.IAddressService;
import com.bh.store.service.ICartService;
import com.bh.store.service.IOrderService;
import com.bh.store.service.IProductService;
import com.bh.store.service.ex.InsertException;
import com.bh.store.service.ex.OrderItemEmptyException;
import com.bh.store.service.ex.OrderNotFoundException;
import com.bh.store.service.ex.UpdateException;
import com.bh.store.vo.CartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class OrderServiceImpl implements IOrderService {
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private IAddressService addressService;
    @Autowired
    private ICartService cartService;
    @Autowired
    private IProductService productService;
    @Override
    public synchronized Order create(Integer aid, Integer uid, String username, Integer[] cids) {
        List<CartVO> list = cartService.getVOByCid(uid, cids);
        Long totalPrice = 0L;
        for (CartVO c:list){
            totalPrice += c.getRealPrice()*c.getNum();
        }
        Address address = addressService.getByAid(aid,uid);
        Order order = new Order();
        order.setUid(uid);
        order.setRecvName(address.getName());
        order.setRecvPhone(address.getPhone());
        order.setRecvProvince(address.getProvinceName());
        order.setRecvCity(address.getCityName());
        order.setRecvArea(address.getAreaName());
        order.setRecvAddress(address.getAddress());
        order.setStatus(0);
        order.setTotalPrice(totalPrice);
        order.setOrderTime(new Date());
        order.setCreatedTime(new Date());
        order.setModifiedTime(new Date());
        order.setModifiedUser(username);
        order.setModifiedUser(username);
        Integer rows = orderMapper.insertOrder(order);
        if (rows!=1){
            throw new InsertException("插入异常");
        }
        for (CartVO c:list){
            //创建订单项数据
            OrderItem orderItem = new OrderItem();
            orderItem.setOid(order.getOid());
            orderItem.setPid(c.getPid());
            orderItem.setTitle(c.getTitle());
            orderItem.setImage(c.getImage());
            orderItem.setPrice(c.getRealPrice());
            orderItem.setNum(c.getNum());
            orderItem.setCreatedTime(new Date());
            orderItem.setModifiedTime(new Date());
            orderItem.setCreatedUser(username);
            orderItem.setModifiedUser(username);
            Integer row = orderMapper.insertOrderItem(orderItem);
            if (row!=1){
                throw new InsertException();
            }
            Product product = productService.findById(c.getPid());
            if(product.getNum() - c.getNum()<0){
                throw new UpdateException();
            }
            productMapper.updateProductsByPid(product.getId(),product.getNum()-c.getNum());
        }
        cartService.deleteCartsByCids(cids);
        return order;
    }

    @Override
    public List<Order> findOrdersByUid(Integer uid) {
        List<Order> orders = orderMapper.findOrdersByUid(uid);
        if (orders ==null){
            throw new OrderNotFoundException("未找到订单");
        }
        return orders;
    }

    @Override
    public List<OrderItem> findOrderItemsByOid(Integer oid) {
        List<OrderItem> items = orderMapper.findOrderItemsByOid(oid);
        if (items==null){
            throw new OrderItemEmptyException("订单信息为空");
        }
        return items;
    }
}
