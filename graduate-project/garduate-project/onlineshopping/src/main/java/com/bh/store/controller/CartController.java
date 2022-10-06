package com.bh.store.controller;

import com.bh.store.entity.Cart;
import com.bh.store.service.ICartService;
import com.bh.store.util.JsonResult;
import com.bh.store.vo.CartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("carts")
public class CartController extends BaseController{

    @Autowired
    private ICartService cartService;

    @RequestMapping("add_to_cart")
    public JsonResult<Void> addTOCart(Integer pid, Integer amount, HttpSession session){
        cartService.addToCart(getuidFromSession(session),pid,amount,getUsernameFromSession(session));
        return new JsonResult<>(OK);
    }

    @RequestMapping({"","/"})
    public JsonResult<List<CartVO>> getVOByUid(HttpSession session){
        List<CartVO> vo = cartService.getVOById(getuidFromSession(session));
        return new JsonResult<>(OK,vo);
    }

    @RequestMapping("{cid}/num/add")
    public JsonResult<Integer> addNum(@PathVariable("cid") Integer cid, HttpSession session){
        Integer num = cartService.addNum(cid, getuidFromSession(session), getUsernameFromSession(session));
        return new JsonResult<>(OK,num);
    }

    @RequestMapping("{cid}/num/reduce")
    public JsonResult<Integer> reduceNum(@PathVariable("cid") Integer cid, HttpSession session){
        Integer num = cartService.reduceNum(cid, getuidFromSession(session), getUsernameFromSession(session));
        return new JsonResult<>(OK,num);
    }

    @RequestMapping("list")
    public JsonResult<List<CartVO>> getVOByCid(Integer[] cids,HttpSession session){
        List<CartVO> data = cartService.getVOByCid(getuidFromSession(session), cids);
        return new JsonResult<>(OK,data);
    }

    @RequestMapping("deleteCart")
    public JsonResult<Void> deleteCartsByCids(Integer cid){
        cartService.deleteCartByCid(cid);
        return new JsonResult<>(OK);
    }

    @RequestMapping("{pid}/getCid")
    public JsonResult<Integer> findCidByPidAndUid(@PathVariable("pid") Integer pid,HttpSession session){
        Integer data = cartService.findCidByPidAndUid(pid, getuidFromSession(session));
        return new JsonResult<>(OK,data);
    }
}
