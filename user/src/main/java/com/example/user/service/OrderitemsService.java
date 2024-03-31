package com.example.user.service;

import com.example.user.po.Orderitems;
public interface OrderitemsService{


    int deleteByPrimaryKey(Integer id);

    int insert(Orderitems record);

    int insertSelective(Orderitems record);

    Orderitems selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Orderitems record);

    int updateByPrimaryKey(Orderitems record);

}
