package com.bh.store.mapper;

import com.bh.store.entity.District;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DIstrictMapperTests {
    @Autowired
    private DistrictMapper districtMapper;

    @Test
    public void findByParent(){
        List<District> list= districtMapper.findByParent("210100");
        for (District d : list){
            System.out.println(d);
        }
    }
    @Test
    public void findNameByCode(){
        String name = districtMapper.findNameByCode("310103");
        System.out.println(name);
    }
}
