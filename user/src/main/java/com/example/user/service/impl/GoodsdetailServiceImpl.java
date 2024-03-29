package com.example.user.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.example.user.po.Goodsdetail;
import com.example.user.dao.GoodsdetailMapper;
import com.example.user.service.GoodsdetailService;
@Service
public class GoodsdetailServiceImpl implements GoodsdetailService{

    @Resource
    private GoodsdetailMapper goodsdetailMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return goodsdetailMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Goodsdetail record) {
        return goodsdetailMapper.insert(record);
    }

    @Override
    public int insertSelective(Goodsdetail record) {
        return goodsdetailMapper.insertSelective(record);
    }

    @Override
    public Goodsdetail selectByPrimaryKey(Integer id) {
        return goodsdetailMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Goodsdetail record) {
        return goodsdetailMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Goodsdetail record) {
        return goodsdetailMapper.updateByPrimaryKey(record);
    }

}
