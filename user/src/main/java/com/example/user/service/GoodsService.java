package com.example.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.user.po.Goods;
import com.example.user.po.Users;
import com.example.user.vo.GoodsVO;

public interface GoodsService extends IService<Goods> {


    int deleteByPrimaryKey(Integer id);

    int insert(Goods record);

    int insertSelective(Goods record);

    GoodsVO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Goods record);

    int updateByPrimaryKey(Goods record);

}
