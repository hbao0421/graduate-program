package com.bh.store.service;

import com.bh.store.entity.User;
import com.bh.store.service.ex.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTests {
    @Autowired
    private IUserService userService;
    @Test
    public void reg(){
        try {
            User user = new User();
            user.setUsername("test01");
            user.setPassword("123");
            userService.reg(user);
            System.out.println("OK");
        } catch (ServiceException e) {
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void login(){
        User user = userService.login("admin","123");
        System.out.println(user);
    }
    @Test
    public void changePassword(){
        userService.changePassword(16,"admin","123","123456");
    }
    @Test
    public void getByUid(){
        System.out.println(userService.getByUid(17));
    }
    @Test
    public void changeInfo(){
        User user = new User();
        user.setPhone("1361372123");
        user.setEmail("1234978439@qq.com");
        user.setGender(0);
        userService.changeInfo(16,"alice",user);
    }
    @Test
    public void changeAvatar(){
        userService.changeAvatar(16,"/upload/test.png","admin");
    }
}
