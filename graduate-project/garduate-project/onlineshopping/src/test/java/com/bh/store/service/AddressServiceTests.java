package com.bh.store.service;

import com.bh.store.entity.Address;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AddressServiceTests {
    @Autowired
    private IAddressService addressService;

    @Test
    public void addNewAddress(){
        Address address = new Address();
        address.setPhone("17321420421");
        address.setName("admin");
        addressService.addNewAddress(19,"管理员",address);
    }
    @Test
    public void setDefault(){
        addressService.setDefault(8,19,"包涵");
    }
    @Test
    public void delete(){
        addressService.delete(11,19,"admin");
    }
}
