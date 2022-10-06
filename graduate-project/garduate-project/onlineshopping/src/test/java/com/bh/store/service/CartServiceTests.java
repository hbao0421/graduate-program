package com.bh.store.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CartServiceTests {
    @Autowired
    private ICartService cartService;
    @Test
    public void addToCart(){
        cartService.addToCart(19,10000011,4,"admin");
        cartService.addToCart(19,10000001,2,"admin");
    }
}
