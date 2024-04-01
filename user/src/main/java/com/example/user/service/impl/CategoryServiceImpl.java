package com.example.user.service.impl;

import com.example.user.vo.RE;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.example.user.dao.CategoryMapper;
import com.example.user.po.Category;
import com.example.user.service.CategoryService;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Resource
    private CategoryMapper categoryMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return categoryMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Category record) {
        return categoryMapper.insert(record);
    }

    @Override
    public RE insertSelective(Category record) {
        if (categoryMapper.insertSelective(record) != 0){
            Category category = categoryMapper.selectByPrimaryKey(record.getId());
            return RE.ok().data("result",category);
        }
        return RE.error();
    }

    @Override
    public RE deleteCategory(Integer id) {
        if (categoryMapper.deleteCategory(id) != 0){
            return RE.ok();
        }
        return RE.error();
    }

    @Override
    public RE selectAll() {
        List<Category> categoryList = categoryMapper.selectAll();
        if (categoryList != null){
            return RE.ok().data("result",categoryList);
        }
        return RE.error();
    }

    @Override
    public Category selectByPrimaryKey(Integer id) {
        return categoryMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Category record) {
        return categoryMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Category record) {
        return categoryMapper.updateByPrimaryKey(record);
    }

}
