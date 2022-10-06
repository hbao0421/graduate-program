package com.bh.store.mapper;

import com.bh.store.entity.District;

import java.util.List;

public interface DistrictMapper {
    //根据父代号查询
    List<District> findByParent(String parent);
    String findNameByCode(String code);
}
