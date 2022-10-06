package com.bh.store.service;

import com.bh.store.entity.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductServiceTests {
    @Autowired
    private IProductService productService;
    @Test
    public void findByCategoryId(){
        List<Product> products = productService.findByCategoryId(241);
        for (Product p :products){
            System.out.println(p);
        }
    }
    @Test
    public void findByTitle(){
        System.out.println(productService.findByTitle("广博(GuangBo)10本装40张A5牛皮纸记事本子日记本办公软抄本GBR0731"));
    }
    @Test
    public void recommendProduct(){
        List<Product> list = productService.recommendProduct(19);
        for (Product p :list){
            System.out.println(p);
        }
    }
}
