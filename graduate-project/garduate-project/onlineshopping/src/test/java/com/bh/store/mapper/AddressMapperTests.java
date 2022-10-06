package com.bh.store.mapper;

import com.bh.store.entity.Address;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AddressMapperTests {
    @Autowired
    private AddressMapper addressMapper;

    @Test
    public void insert(){
        Address address = new Address();
        address.setUid(19);
        address.setPhone("17321420421");
        address.setName("admin");
        addressMapper.insert(address);
    }

    @Test
    public void countByUid(){
        Integer count = addressMapper.countByUid(19);
        System.out.println(count);
    }
    @Test
    public void findByUid(){
        List<Address> addresses = addressMapper.findByUid(19);
        for (Address address : addresses){
            System.out.println(address);
        }
    }
    @Test
    public void findByAid(){
        System.out.println(addressMapper.findByAid(8));
    }
    @Test
    public void updateNonDefault(){
        System.out.println(addressMapper.updateNonDefault(19));
    }
    @Test
    public void updateDefaultByAid(){
        System.out.println(addressMapper.updateDefaultByAid(9, "admin", new Date()));
    }
    @Test
    public void deleteByAid(){
        addressMapper.deleteByAid(10);
    }
    @Test
    public void findLastModified(){
        System.out.println(addressMapper.findLastModified(19));
    }
}
