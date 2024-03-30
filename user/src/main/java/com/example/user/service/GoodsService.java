package com.example.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.user.po.Goods;
import com.example.user.po.Users;
import com.example.user.vo.GoodsVO;

public interface GoodsService extends IService<Goods> {


    int deleteByPrimaryKey(Integer id);

    int insert(Goods record);

//    上传商品信息
    int insertSelective(Goods record);

//    根据主键查找商品信息
    GoodsVO selectByPrimaryKey(Integer id);

//    修改商品信息
    int updateByPrimaryKeySelective(Goods record);

//    删除商品
    int updateByPrimaryKey(Goods record);

}
