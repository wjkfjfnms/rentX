package com.example.user.service;

import com.example.user.po.Category;
import com.example.user.vo.RE;



public interface CategoryService{


    int deleteByPrimaryKey(Integer id);

    int insert(Category record);

//    添加类别
    RE insertSelective(Category record);

//    删除类别
    RE deleteCategory(Integer id);

//    返回全部类别
    RE selectAll();

    Category selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);

}
