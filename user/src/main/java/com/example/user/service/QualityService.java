package com.example.user.service;

import com.example.user.po.Quality;
public interface QualityService{


    int deleteByPrimaryKey(Integer id);

    int insert(Quality record);

    int insertSelective(Quality record);

    Quality selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Quality record);

    int updateByPrimaryKey(Quality record);

}
