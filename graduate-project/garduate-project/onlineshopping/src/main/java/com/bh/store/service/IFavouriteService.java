package com.bh.store.service;

import com.bh.store.entity.Favourite;

import java.util.List;

public interface IFavouriteService {
    void addToFavourite(Integer uid,String username,Integer pid);
    List<Favourite> getFavouritesByUid(Integer uid);
    void deleteFavouriteByPid(Integer pid,Integer uid);
}
