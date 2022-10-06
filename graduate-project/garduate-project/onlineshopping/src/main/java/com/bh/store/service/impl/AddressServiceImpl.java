package com.bh.store.service.impl;

import com.bh.store.entity.Address;
import com.bh.store.mapper.AddressMapper;
import com.bh.store.service.IAddressService;
import com.bh.store.service.IDistrictService;
import com.bh.store.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AddressServiceImpl implements IAddressService {
    @Autowired
    private AddressMapper addressMapper;
    @Autowired
    private IDistrictService districtService;

    @Value("${user.address.max-count}")
    private Integer maxCount;

    @Override
    public void addNewAddress(Integer uid, String username, Address address) {
        Integer count = addressMapper.countByUid(uid);
        if (count>maxCount){
            throw new AddressCountLimitException("地址数量最多为"+maxCount);
        }

        address.setProvinceName(districtService.getNameByCode(address.getProvinceCode()));
        address.setCityName(districtService.getNameByCode(address.getCityCode()));
        address.setAreaName(districtService.getNameByCode(address.getAreaCode()));

        address.setUid(uid);
        Integer isDefault = count == 0 ? 1:0;
        address.setIsDefault(isDefault);
        address.setCreatedUser(username);
        address.setCreatedTime(new Date());
        address.setModifiedUser(username);
        address.setModifiedTime(new Date());

        Integer rows = addressMapper.insert(address);
        if (rows!=1){
            throw new InsertException("插入时产生异常");
        }
    }

    @Override
    public List<Address> getByUid(Integer uid) {
        List<Address> list = addressMapper.findByUid(uid);
        for (Address address:list){
            address.setProvinceCode(null);
            address.setCityCode(null);
            address.setAreaCode(null);
            address.setTel(null);
            address.setIsDefault(null);
            address.setCreatedTime(null);
            address.setCreatedUser(null);
            address.setModifiedTime(null);
            address.setModifiedUser(null);
        }
        return list;
    }

    @Override
    public void setDefault(Integer aid, Integer uid, String username) {
        Address address = addressMapper.findByAid(aid);
        if (address == null){
            throw new AddressNotFoundException("收货地址未找到");
        }
        if (!address.getUid().equals(uid)){
            throw new AccessDeniedException("非法访问");
        }
        Integer rows = addressMapper.updateNonDefault(uid);
        if (rows<1){
            throw new UpdateException("更新数据异常");
        }
        Integer rowsupdate = addressMapper.updateDefaultByAid(aid, username, new Date());
        if (rowsupdate!=1){
            throw new UpdateException("更新数据异常");
        }
    }

    @Override
    public void delete(Integer aid, Integer uid, String username) {
        Address result = addressMapper.findByAid(aid);
        if (result==null){
            throw new AddressNotFoundException("地址未找到");
        }
        if (!result.getUid().equals(uid)){
            throw new AccessDeniedException("非法访问");
        }
        Integer rows = addressMapper.deleteByAid(aid);
        if (rows!=1){
            throw new DeleteException("删除异常");
        }
        if (result.getIsDefault()==1){
            Address address = addressMapper.findLastModified(uid);
            Integer row = addressMapper.updateDefaultByAid(address.getAid(), username, new Date());
            if (row!=1){
                throw new UpdateException("更新数据产生未知异常");
            }
        }
    }

    @Override
    public Address getByAid(Integer aid,Integer uid) {
        Address address = addressMapper.findByAid(aid);
        if (address ==null){
            throw new  AddressNotFoundException();
        }
        if (!address.getUid().equals(uid)){
            throw new AccessDeniedException();
        }
        return address;
    }
}
