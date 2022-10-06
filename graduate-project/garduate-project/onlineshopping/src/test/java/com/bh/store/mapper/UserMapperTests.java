package com.bh.store.mapper;

import com.bh.store.entity.User;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserMapperTests {
    @Autowired
    private UserMapper userMapper;
    @Test
    public void insert(){
        User user = new User();
        user.setUsername("admin");
        user.setPassword("123");
        System.out.println(userMapper.insert(user));
    }
    @Test
    public void findByUsername(){
        User user =userMapper.findByUsername("tim");
        System.out.println(user);
    }
    @Test
    public void updatePasswordByUid(){
        userMapper.updatePasswordByUid(16,"123","administrator",new Date());
    }
    @Test
    public void findByUid(){
        User user = userMapper.findByUid(15);
        System.out.println(user);
    }
    @Test
    public void updateInfoByUid(){
        User user = new User();
        user.setUsername("tim");
        user.setPassword("123");
        userMapper.insert(user);
        user.setPhone("17321420421");
        user.setEmail("1240593799@qq.com");
        user.setGender(1);
        userMapper.updateInfoByUid(user);
    }
    @Test
    public void UpdateAvatarByUid(){
        userMapper.updateAvatarByUid(16,"/upload/avatar.png","admin",new Date());
    }
}
