package com.example.user.service;

import com.example.user.po.Goodsdetail;
public interface GoodsdetailService{


    int deleteByPrimaryKey(Integer id);

    int insert(Goodsdetail record);

    int insertSelective(Goodsdetail record);

    Goodsdetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Goodsdetail record);

    int updateByPrimaryKey(Goodsdetail record);

}
