package com.bh.store.mapper;

import com.bh.store.entity.Address;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/*收货地址持久层方法*/
public interface AddressMapper {
    /**
     * 插入用户的收货地址数据
     * @param address 收货地址数据
     * @return 受影响行数
     */
    Integer insert(Address address);

    /**
     * 统计用户收货地址数量
     * @param uid 用户id
     * @return 当前用户收货地址总数
     */
    Integer countByUid(Integer uid);

    /**
     * 用uid查询用户收货地址数据
     * @param uid 用户id
     * @return 地址列表
     */
    List<Address> findByUid(Integer uid);

    /**
     * 根据aid查询地址
     * @param aid 地址Id
     * @return 地址数据
     */
    Address findByAid(Integer aid);

    /**
     * 将用户的所有地址设为非默认地址
     * @param uid 用户id
     * @return 受影响行数
     */
    Integer updateNonDefault(Integer uid);

    /**
     * 根据aid将地址设为默认
     * @param aid 地址id
     * @return 受影响行数
     */
    Integer updateDefaultByAid(@Param("aid") Integer aid,@Param("modifiedUser") String modifiedUser,@Param("modifiedTime") Date modifiedTime);

    /**
     * 根据aid删除收货地址
     * @param aid 地址id
     * @return 影响行数
     */
    Integer deleteByAid(Integer aid);

    /**
     * 用uid查询最后被修改的收货地址数据
     * @param uid 用户id
     * @return 收货地址
     */
    Address findLastModified(Integer uid);
}
