package com.bh.store.mapper;

import com.bh.store.entity.Product;

import java.util.List;

public interface ProductMapper {
    List<Product> findHotList();
    Product findById(Integer id);
    List<Product> findByCategoryId(Integer categoryId);
    Product findByTitle(String title);
    List<Product> findProductsByPid(Integer[] pids);
    List<Product> findAllProducts();
    Integer updateProductsByPid(Integer pid,Integer num);
}
