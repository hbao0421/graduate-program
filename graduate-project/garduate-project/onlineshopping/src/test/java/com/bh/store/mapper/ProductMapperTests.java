package com.bh.store.mapper;

import com.bh.store.entity.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductMapperTests {
    @Autowired
    private ProductMapper productMapper;
    @Test
    public void findByCategoryId(){
        List<Product> products = productMapper.findByCategoryId(238);
        for (Product p:products){
            System.out.println(p);
        }
    }
    @Test
    public void findByTitle(){
        Product product = productMapper.findByTitle("广博(GuangBo)10本装40张A5牛皮纸记事本子日记本办公软抄本GBR0731");
        System.out.println(product);
    }
    @Test
    public void findProductsByPid(){
        Integer[] pids = {10000001,10000002,10000003,10000004};
        System.out.println(productMapper.findProductsByPid(pids));
    }
    @Test
    public void findAllProducts(){
        List<Product> products = productMapper.findAllProducts();
        for (Product p:products){
            System.out.println(p);
        }
    }
    @Test
    public void updateProductsByPid(){
        productMapper.updateProductsByPid(10000001,99998);
    }

}
