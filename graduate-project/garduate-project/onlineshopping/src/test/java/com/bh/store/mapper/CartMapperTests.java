package com.bh.store.mapper;

import com.bh.store.entity.Cart;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CartMapperTests {
    @Autowired
    private CartMapper cartMapper;

    @Test
    public void insert(){
        Cart cart = new Cart();
        cart.setUid(19);
        cart.setPid(10000011);
        cart.setNum(2);
        cart.setPrice(1000L);
        cartMapper.insert(cart);
    }

    @Test
    public void updateNumByCid(){
        cartMapper.updateNumByCid(1,4,"admin",new Date());
    }

    @Test
    public void findByUidAndPid(){
        Cart data = cartMapper.findByUidAndPid(19, 10000011);
        System.out.println(data);
    }

    @Test
    public void findVOById(){
        System.out.println(cartMapper.findVOById(19));
    }

    @Test
    public void findByCid(){
        System.out.println(cartMapper.findByCid(1));
    }

    @Test
    public void findVOByCid(){
        Integer[] cids = {1,2,3,4};
        System.out.println(cartMapper.findVOByCid(cids));
    }

    @Test
    public void deleteCartsByPids(){
        Integer[] cids = {11,12};
        cartMapper.deleteCartsByCids(cids);
    }

    @Test
    public void findCidByPidAndUid(){
        Integer cidByPidAndUid = cartMapper.findCidByPidAndUid(10000003, 20);
        System.out.println(cidByPidAndUid);
    }
}
