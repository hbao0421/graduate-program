package com.bh.store.service;

import com.bh.store.entity.Favourite;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class FavouriteServiceTests {
    @Autowired
    private IFavouriteService favouriteService;
    @Test
    public void insert(){
        favouriteService.addToFavourite(19,"admin",10000001);
    }

    @Test
    public void getFavouritesByUid(){
        List<Favourite> favourites = favouriteService.getFavouritesByUid(19);
        for (Favourite f:favourites){
            System.out.println(f);
        }
    }
}
