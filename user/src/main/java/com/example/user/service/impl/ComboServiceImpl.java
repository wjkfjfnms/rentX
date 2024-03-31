package com.example.user.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.example.user.dao.ComboMapper;
import com.example.user.po.Combo;
import com.example.user.service.ComboService;
@Service
public class ComboServiceImpl implements ComboService{

    @Resource
    private ComboMapper comboMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return comboMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Combo record) {
        return comboMapper.insert(record);
    }

    @Override
    public int insertSelective(Combo record) {
        return comboMapper.insertSelective(record);
    }

    @Override
    public Combo selectByPrimaryKey(Integer id) {
        return comboMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Combo record) {
        return comboMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Combo record) {
        return comboMapper.updateByPrimaryKey(record);
    }

}
