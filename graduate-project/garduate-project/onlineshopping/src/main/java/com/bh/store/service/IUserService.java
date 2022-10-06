package com.bh.store.service;

import com.bh.store.entity.User;

public interface IUserService {
    /**
     * 用户注册方法
     */
    void reg(User user);

    /**
     * 用户登录
     * @param username 用户名
     * @param password 密码
     * @return User对象
     */
    User login(String username,String password);

    /**
     * 修改密码
     * @param uid 用户Id
     * @param username 用户名
     * @param oldPassword 用户旧密码
     * @param newPassword 用户新密码
     */
    void changePassword(Integer uid,String username,String oldPassword,String newPassword);

    /**
     * 根据用户信息进行查询
     * @param uid 用户的id
     * @return 查询到的用户对象
     */
    User getByUid(Integer uid);

    /**
     * 更新用户数据
     */
    void changeInfo(Integer uid,String username,User user);

    /**
     * 修改头像
     * @param uid 用户Id
     * @param avatar 头像地址
     * @param username 用户名
     */
    void changeAvatar(Integer uid,String avatar,String username);
}
