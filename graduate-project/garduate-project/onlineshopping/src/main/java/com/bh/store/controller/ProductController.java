package com.bh.store.controller;

import com.bh.store.entity.Product;
import com.bh.store.service.IProductService;
import com.bh.store.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("products")
public class ProductController extends BaseController{
    @Autowired
    private IProductService productService;

    @RequestMapping("hot_list")
    public JsonResult<List<Product>> getHotList(){
        List<Product> data = productService.findHotList();
        return new JsonResult<List<Product>>(OK,data);
    }
    @GetMapping("{id}/details")
    public JsonResult<Product> getById(@PathVariable("id") Integer id){
        Product data = productService.findById(id);
        return new JsonResult<Product>(OK,data);
    }
    @RequestMapping("category")
    public  JsonResult<List<Product>> getByCategory(Integer categoryId){
        List<Product> data = productService.findByCategoryId(categoryId);
        return new JsonResult<>(OK,data);
    }
    @RequestMapping("search")
    public JsonResult<Product> search(String search){
        Product data = productService.findByTitle(search);
        return new JsonResult<>(OK,data);
    }
    @RequestMapping("recommend")
    public JsonResult<List<Product>> getRecommend(HttpSession session){
        List<Product> data = productService.recommendProduct(getuidFromSession(session));
        return new JsonResult<>(OK,data);
    }
    @RequestMapping({"","/"})
    public JsonResult<List<Product>> getAllProducts(){
        List<Product> data = productService.findAllProducts();
        return new JsonResult<>(OK,data);
    }
}
