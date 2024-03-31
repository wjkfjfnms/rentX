package com.example.user.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.example.user.dao.OrderitemsMapper;
import com.example.user.po.Orderitems;
import com.example.user.service.OrderitemsService;
@Service
public class OrderitemsServiceImpl implements OrderitemsService{

    @Resource
    private OrderitemsMapper orderitemsMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return orderitemsMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Orderitems record) {
        return orderitemsMapper.insert(record);
    }

    @Override
    public int insertSelective(Orderitems record) {
        return orderitemsMapper.insertSelective(record);
    }

    @Override
    public Orderitems selectByPrimaryKey(Integer id) {
        return orderitemsMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Orderitems record) {
        return orderitemsMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Orderitems record) {
        return orderitemsMapper.updateByPrimaryKey(record);
    }

}
