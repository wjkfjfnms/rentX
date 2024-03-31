package com.example.user.service;

import com.example.user.po.Goodsdetail;
import com.example.user.vo.RE;

public interface GoodsdetailService{

//删除图片
    RE deleteByPrimaryKey(Integer id);

    int insert(Goodsdetail record);

    int insertSelective(Goodsdetail record);

    Goodsdetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Goodsdetail record);

//    修改图片
    RE updateByPrimaryKey(Goodsdetail record);

}
