package com.example.user.service;

import com.example.user.po.Address;
import com.example.user.vo.PagePara;
import com.example.user.vo.RE;

public interface AddressService{


    int deleteByPrimaryKey(Integer id);

    int insert(Address record);

    RE insertSelective(Address record);

    Address selectByPrimaryKey(Integer id);

    RE updateByPrimaryKeySelective(Address record);

    RE updateByPrimaryKey(Integer id);

    RE findAll(Integer userId, PagePara pagePara);

}
