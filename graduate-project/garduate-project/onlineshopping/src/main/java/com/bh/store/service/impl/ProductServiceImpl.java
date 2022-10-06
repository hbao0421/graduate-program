package com.bh.store.service.impl;

import com.bh.store.entity.Favourite;
import com.bh.store.entity.Product;
import com.bh.store.mapper.FavouriteMapper;
import com.bh.store.mapper.ProductMapper;
import com.bh.store.service.IProductService;
import com.bh.store.service.ex.ProductNotFoundException;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductServiceImpl implements IProductService {
    @Autowired
    ProductMapper productMapper;

    @Autowired
    FavouriteMapper favouriteMapper;

    @Override
    public List<Product> findHotList() {
        List<Product> list = productMapper.findHotList();
        for (Product product : list){
            product.setPriority(null);
            product.setCreatedUser(null);
            product.setCreatedTime(null);
            product.setModifiedUser(null);
            product.setModifiedTime(null);
        }
        return list;
    }

    @Override
    public Product findById(Integer id) {
        Product product = productMapper.findById(id);
        if (product==null){
            throw new ProductNotFoundException("商品没找到");
        }
        return product;
    }

    @Override
    public List<Product> findByCategoryId(Integer categoryId) {
        List<Product> products = productMapper.findByCategoryId(categoryId);
        if (products==null){
            throw new ProductNotFoundException("没有该类商品");
        }
        return products;
    }

    @Override
    public Product findByTitle(String title) {
        Product product = productMapper.findByTitle(title);
        return product;
    }

    @Override
    public List<Product> recommendProduct(Integer uid) {
        List<Favourite> allFav = favouriteMapper.getAllFav();
        int size = allFav.toArray().length+1;
        int[][] userProductArray = new int[size][size];
        int i = 1;
        int j = 1;
        int userRange = 4;//兴趣相似用户数量
        HashMap<Integer,Double> simMap = new HashMap<>();//用户相似度
        HashMap<Integer,Double> productInterestMap = new HashMap<>();
        for (Favourite fav:allFav){
            if(!ArrayUtils.contains(userProductArray[0],fav.getProductId())){
                userProductArray[0][i] = fav.getProductId();
                i++;
            }
            if (!ArrayHasElement(userProductArray,fav.getUid())){
                userProductArray[j][0] = fav.getUid();
                j++;
            }
            userProductArray[getUserIndex(userProductArray,fav.getUid())][getProductIndex(userProductArray,fav.getProductId())] = 1;
        }
        int[] targetUser = userProductArray[getUserIndex(userProductArray,uid)];
        for (int[] user:userProductArray){
            int sameCount = 0;//相同物品数
            int otherCount=0;//其他用户物品数
            int targetUserCount=0;//目标用户物品数
            if (user[0] == uid){
                continue;
            }
            for (int k=1;k<userProductArray.length;k++){
                if (targetUser[k]==1 && user[k]==1){
                    sameCount ++;
                }
                if (targetUser[k] ==1){
                    targetUserCount++;
                }
                if (user[k] == 1){
                    otherCount++;
                }
            }
            double userSimilarity = sameCount/Math.sqrt(targetUserCount*otherCount);
            simMap.put(user[0],userSimilarity);
        }
        simMap.remove(0);
        ArrayList<Map.Entry<Integer,Double>> list = new ArrayList<>(simMap.entrySet());
        list.sort((o1, o2) -> -o1.getValue().compareTo(o2.getValue()));
        for (int k=0;k<userRange;k++){
            int userId = list.get(k).getKey();
            double userSimilarity = list.get(k).getValue();
            for (int l=1;l<userProductArray.length;l++){
                if (targetUser[l]!=1&&userProductArray[getUserIndex(userProductArray,userId)][l]==1){
                    if (!productInterestMap.containsKey(userProductArray[0][l])){
                        productInterestMap.put(userProductArray[0][l],userSimilarity);
                    }else {
                        double oldInterest = productInterestMap.get(userProductArray[0][l]);
                        productInterestMap.put(userProductArray[0][l],oldInterest+userSimilarity);
                    }
                }
            }
        }
        ArrayList<Map.Entry<Integer,Double>> productInterestList = new ArrayList<>(productInterestMap.entrySet());
        productInterestList.sort((o1, o2) -> -o1.getValue().compareTo(o2.getValue()));
        int recommendProductsNum = 5;//推荐商品数
        if (productInterestList.toArray().length<recommendProductsNum){
            recommendProductsNum = productInterestList.toArray().length;
        }
        Integer[] recommendProducts = new Integer[recommendProductsNum];
        for (int k=0;k<recommendProductsNum;k++){
            recommendProducts[k] = productInterestList.get(k).getKey();
        }
        for(Map.Entry<Integer,Double> m : productInterestList){
            System.out.println(m.getKey()+"="+m.getValue());
        }
        System.out.println(Arrays.toString(recommendProducts));
        return productMapper.findProductsByPid(recommendProducts);
    }

    @Override
    public List<Product> findAllProducts() {
        return productMapper.findAllProducts();
    }

    public Boolean ArrayHasElement(int[][] array,int e){
        for (int i=1;i<array.length;i++){
            if (array[i][0] == e){
                return true;
            }
        }
        return false;
    }

    public Integer getProductIndex(int[][] array,int productId){
        for (int i=1;i<array.length;i++){
            if (array[0][i] == productId){
                return i;
            }
        }
        return 0;
    }

    public Integer getUserIndex(int[][] array,int userId){
        for (int i=1;i<array.length;i++){
            if (array[i][0] == userId){
                return i;
            }
        }
        return 0;
    }
}
