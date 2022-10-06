package com.bh.store.service;

import com.bh.store.entity.District;

import java.util.List;

public interface IDistrictService {
    /**
     * 根据父代号查询区域信息
     * @param parent 父代号
     * @return 区数组
     */
    List<District> getByParent(String parent);

    String getNameByCode(String code);
}
