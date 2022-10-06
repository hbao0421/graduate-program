package com.bh.store.mapper;

import com.bh.store.entity.Favourite;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class FavouriteMapperTests {
    @Autowired
    private FavouriteMapper favouriteMapper;

    @Test
    public void insert(){
        Favourite favourite = new Favourite();
        favourite.setUid(1);
        favourite.setProductId(1);
        favourite.setCategoryId(1);
        favourite.setItemType("x");
        favourite.setTitle("x");
        favourite.setSellPoint("x");
        favourite.setPrice(12L);
        favourite.setNum(99);
        favourite.setImage("/");
        favourite.setStatus(0);
        favourite.setPriority(1);
        Integer integer = favouriteMapper.insert(favourite);
        System.out.println(integer);
    }

    @Test
    public void getFavouriteByUid(){
        List<Favourite> favourite = favouriteMapper.getFavouriteByUid(19);
        System.out.println(favourite);
    }

    @Test
    public void getAllFav(){
        List<Favourite> allFav = favouriteMapper.getAllFav();
        System.out.println(allFav);
    }

    @Test
    public void deleteFavouriteByPid(){
        Integer integer = favouriteMapper.deleteFavouriteByPid(10000003, 19);
        System.out.println(integer);
    }
}
