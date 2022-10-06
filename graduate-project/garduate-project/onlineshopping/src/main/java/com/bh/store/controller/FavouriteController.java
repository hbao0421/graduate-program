package com.bh.store.controller;

import com.bh.store.entity.Favourite;
import com.bh.store.service.IFavouriteService;
import com.bh.store.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("favourites")
public class FavouriteController extends BaseController{
    @Autowired
    private IFavouriteService favouriteService;

    @RequestMapping("add_to_favourite")
    public JsonResult<Void> addToFavourite(HttpSession session,Integer pid){
        favouriteService.addToFavourite(getuidFromSession(session),getUsernameFromSession(session),pid);
        return new JsonResult<>(OK);
    }

    @RequestMapping({"", "/"})
    public JsonResult<List<Favourite>> getFavouritesByUid(HttpSession session){
        List<Favourite> data = favouriteService.getFavouritesByUid(getuidFromSession(session));
        return new JsonResult<>(OK,data);
    }

    @RequestMapping("delete_from_favourites")
    public JsonResult<Void> deleteFromFavourites(Integer pid,HttpSession session){
        favouriteService.deleteFavouriteByPid(pid,getuidFromSession(session));
        return new JsonResult<>(OK);
    }
}
