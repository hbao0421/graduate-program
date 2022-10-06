package com.bh.store.service;

import com.bh.store.entity.Product;

import java.util.List;
import java.util.Map;

public interface IProductService {
    List<Product> findHotList();
    Product findById(Integer id);
    List<Product> findByCategoryId(Integer categoryId);
    Product findByTitle(String title);
    List<Product> recommendProduct(Integer uid);
    List<Product> findAllProducts();
}
