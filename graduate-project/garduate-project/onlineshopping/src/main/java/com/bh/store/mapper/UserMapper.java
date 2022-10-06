package com.bh.store.mapper;

import com.bh.store.entity.User;

import java.util.Date;

public interface UserMapper {
    /**
     * 插入用户数据
     * @param user 用户数据
     * @return 受影响行数，根据行数判断是否执行成功
     */
    Integer insert(User user);

    /**
     * 根据用户名查询
     * @param username 用户名
     * @return 如果扎到对应用户返回数据，没有找到返回Null
     */
    User findByUsername(String username);

    /**
     * 根据uid修改密码
     * @param uid 用户id
     * @return 受到影响行数
     */
    Integer updatePasswordByUid(Integer uid,String password,String modifiedUser,Date modifiedTime);

    /**
     * 根据用户id查询
     * @param uid 用户id
     * @return 用户对象 找不到返回null
     */
    User findByUid(Integer uid);

    /**
     * 更新用户数据
     * @param user 用户对象
     * @return 受影响行数
     */
    Integer updateInfoByUid(User user);

    /**
     * 根据用户uid修改头像
     * @param uid 用户id
     * @param avatar 用户头像
     * @param modifiedUser 修改用户
     * @param modifiedTime 修改时间
     * @return 影响行数
     */
    Integer updateAvatarByUid(Integer uid,String avatar,String modifiedUser,Date modifiedTime);
}
