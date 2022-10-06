package com.bh.store.service.impl;

import com.bh.store.entity.Cart;
import com.bh.store.mapper.CartMapper;
import com.bh.store.mapper.ProductMapper;
import com.bh.store.service.ICartService;
import com.bh.store.service.ex.*;
import com.bh.store.vo.CartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class CartServiceImpl implements ICartService {
    @Autowired
    CartMapper cartMapper;
    @Autowired
    ProductMapper productMapper;
    @Override
    public void addToCart(Integer uid, Integer pid, Integer amount, String username) {
        Cart res = cartMapper.findByUidAndPid(uid, pid);
        if (res==null){
            Cart cart= new Cart();
            cart.setUid(uid);
            cart.setPid(pid);
            cart.setNum(amount);
            cart.setPrice(productMapper.findById(pid).getPrice());
            cart.setCreatedUser(username);
            cart.setCreatedTime(new Date());
            cart.setModifiedUser(username);
            cart.setModifiedTime(new Date());
            Integer rows = cartMapper.insert(cart);
            if (rows!=1){
                throw new InsertException("插入产生异常");
            }
        }else {
            Integer rows = cartMapper.updateNumByCid(res.getCid(), amount + res.getNum(), username, new Date());
            if (rows!=1){
                throw new UpdateException("更新异常");
            }
        }
    }

    @Override
    public List<CartVO> getVOById(Integer uid) {
        return cartMapper.findVOById(uid);
    }

    @Override
    public Integer addNum(Integer cid, Integer uid, String username) {
        Cart cart = cartMapper.findByCid(cid);
        if (cart==null){
            throw new CartNotFoundException("未找到购物车数据");
        }
        if (!cart.getUid().equals(uid)){
            throw new AccessDeniedException("非法访问");
        }
        Integer rows = cartMapper.updateNumByCid(cid, cart.getNum() + 1, username, new Date());
        if (rows!=1){
            throw new UpdateException("更新异常");
        }
        return cart.getNum()+1;
    }

    @Override
    public Integer reduceNum(Integer cid, Integer uid, String username) {
        Cart cart = cartMapper.findByCid(cid);
        if (cart==null){
            throw new CartNotFoundException("未找到购物车数据");
        }
        if (!cart.getUid().equals(uid)){
            throw new AccessDeniedException("非法访问");
        }
        if (cart.getNum()==1){
            throw new UpdateException("最小数量为1");
        }
        Integer rows = cartMapper.updateNumByCid(cid, cart.getNum() - 1, username, new Date());
        if (rows!=1){
            throw new UpdateException("更新异常");
        }
        return cart.getNum()-1;
    }

    @Override
    public List<CartVO> getVOByCid(Integer uid,Integer[] cids) {
        List<CartVO> list = cartMapper.findVOByCid(cids);
        Iterator<CartVO> iterator = list.iterator();
        while (iterator.hasNext()){
            CartVO cartVO = iterator.next();
            if (!cartVO.getUid().equals(uid)){
                list.remove(cartVO);
            }
        }
        return list;
    }

    @Override
    public void deleteCartsByCids(Integer[] cids) {
        Integer rows = cartMapper.deleteCartsByCids(cids);
        if (rows == 0){
            throw new DeleteException();
        }
    }

    @Override
    public void deleteCartByCid(Integer cid) {
        Integer row = cartMapper.deleteCartByCid(cid);
        if (row == 0){
            throw new DeleteException();
        }
    }

    @Override
    public Integer findCidByPidAndUid(Integer pid, Integer uid) {
        Integer cid = cartMapper.findCidByPidAndUid(pid, uid);
        return cid;
    }
}
