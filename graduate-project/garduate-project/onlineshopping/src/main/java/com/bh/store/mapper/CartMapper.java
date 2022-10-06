package com.bh.store.mapper;

import com.bh.store.entity.Cart;
import com.bh.store.vo.CartVO;

import java.util.Date;
import java.util.List;

public interface CartMapper {
    /**
     * 插入购物车数据
     * @param cart 购物车数据
     * @return 影响行数
     */
    Integer insert(Cart cart);

    /**
     * 更新某件商品数量
     * @param cid 购物车id
     * @param num 更新数量
     * @param modifiedUser 更新用户
     * @param modifiedTime 更新时间
     * @return 影响行数
     */
    Integer updateNumByCid(Integer cid, Integer num, String modifiedUser, Date modifiedTime);

    /**
     * 根据用户id和商品Id查询购物车数据
     * @param uid 用户id
     * @param pid 商品id
     * @return 购物车数据
     */
    Cart findByUidAndPid(Integer uid,Integer pid);

    List<CartVO> findVOById(Integer uid);

    Cart findByCid(Integer cid);

    List<CartVO> findVOByCid(Integer[] cids);

    Integer deleteCartsByCids(Integer[] cids);

    Integer deleteCartByCid(Integer cid);

    Integer findCidByPidAndUid(Integer pid,Integer uid);
}
