package com.example.user.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.example.user.dao.QualityMapper;
import com.example.user.po.Quality;
import com.example.user.service.QualityService;
@Service
public class QualityServiceImpl implements QualityService{

    @Resource
    private QualityMapper qualityMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return qualityMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Quality record) {
        return qualityMapper.insert(record);
    }

    @Override
    public int insertSelective(Quality record) {
        return qualityMapper.insertSelective(record);
    }

    @Override
    public Quality selectByPrimaryKey(Integer id) {
        return qualityMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Quality record) {
        return qualityMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Quality record) {
        return qualityMapper.updateByPrimaryKey(record);
    }

}
