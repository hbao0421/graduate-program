package com.bh.store.mapper;

import com.bh.store.entity.Favourite;

import java.util.List;

public interface FavouriteMapper {
    Integer insert(Favourite favourite);
    List<Favourite> getFavouriteByUid(Integer uid);
    Favourite getFavouriteByPid(Integer pid,Integer uid);
    Integer deleteFavouriteByPid(Integer pid,Integer uid);
    List<Favourite> getAllFav();
}
