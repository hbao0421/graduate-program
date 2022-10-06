package com.bh.store.service;

import com.bh.store.vo.CartVO;

import java.util.List;

public interface ICartService {
    /**
     * 将商品添加到购物车
     * @param uid 用户id
     * @param pid 商品id
     * @param amount 商品数量
     * @param username 用户名
     */
    void addToCart(Integer uid,Integer pid,Integer amount,String username);

    /**
     * 通过id查找商品
     * @param uid 用户id
     * @return 商品vo类
     */
    List<CartVO> getVOById(Integer uid);

    /**
     * 更新用户的购物车数据数量
     * @param cid 购物车id
     * @param uid 用户id
     * @param username 用户名
     * @return 更新成功后的数量
     */
    Integer addNum(Integer cid,Integer uid,String username);

    Integer reduceNum(Integer cid,Integer uid,String username);

    List<CartVO> getVOByCid(Integer uid,Integer[] cids);

    void deleteCartsByCids(Integer[] cids);

    void deleteCartByCid(Integer cid);

    Integer findCidByPidAndUid(Integer pid,Integer uid);
}
