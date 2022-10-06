package com.bh.store.service.impl;

import com.bh.store.entity.Favourite;
import com.bh.store.entity.Product;
import com.bh.store.mapper.FavouriteMapper;
import com.bh.store.mapper.ProductMapper;
import com.bh.store.service.IFavouriteService;
import com.bh.store.service.ex.DeleteException;
import com.bh.store.service.ex.InsertException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class FavouriteServiceImpl implements IFavouriteService {

    @Autowired
    private FavouriteMapper favouriteMapper;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public void addToFavourite(Integer uid, String username, Integer pid) {
        List<Favourite> favourites = favouriteMapper.getFavouriteByUid(uid);
        for (Favourite f :favourites){
            if (Objects.equals(f.getProductId(), pid)){
                throw new InsertException("商品已收藏");
            }
        }
        Favourite favourite = new Favourite();
        Product product = productMapper.findById(pid);
        favourite.setUid(uid);
        favourite.setProductId(pid);
        favourite.setCategoryId(product.getCategoryId());
        favourite.setItemType(product.getItemType());
        favourite.setTitle(product.getTitle());
        favourite.setSellPoint(product.getSellPoint());
        favourite.setPrice(product.getPrice());
        favourite.setNum(product.getNum());
        favourite.setImage(product.getImage());
        favourite.setStatus(product.getStatus());
        favourite.setPriority(product.getPriority());
        favourite.setCreatedTime(new Date());
        favourite.setModifiedTime(new Date());
        favourite.setCreatedUser(username);
        favourite.setModifiedUser(username);
        Integer rows = favouriteMapper.insert(favourite);
        if (rows!=1){
            throw new InsertException();
        }
    }

    @Override
    public List<Favourite> getFavouritesByUid(Integer uid){
        List<Favourite> favourite = favouriteMapper.getFavouriteByUid(uid);
        return favourite;
    }

    @Override
    public void deleteFavouriteByPid(Integer pid,Integer uid) {
        System.out.println(uid);
        if (favouriteMapper.getFavouriteByPid(pid,uid)==null){
            throw new DeleteException();
        }
        favouriteMapper.deleteFavouriteByPid(pid,uid);
    }
}
