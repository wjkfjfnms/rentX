package com.example.user.service;

import com.example.user.po.Combo;
public interface ComboService{


    int deleteByPrimaryKey(Integer id);

    int insert(Combo record);

    int insertSelective(Combo record);

    Combo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Combo record);

    int updateByPrimaryKey(Combo record);

}
