package com.example.user.controller;

import com.example.user.po.Category;
import com.example.user.service.CategoryService;
import com.example.user.vo.RE;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "/api/category")
@Api(tags = {"类别接口"})
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    //    添加类别
    @ApiOperation(value = "添加类别")
    @PostMapping("/insertSelective")
    public RE insertSelective(@Validated @RequestBody Category record){
        return categoryService.insertSelective(record);
    }


    //    删除类别
    @ApiOperation(value = "删除类别")
    @PutMapping("/deleteCategory")
    public RE deleteCategory(Integer id){
        return categoryService.deleteCategory(id);
    }

    //    返回全部类别
    @ApiOperation(value = "返回全部类别")
    @GetMapping("/selectAll")
    public RE selectAll(){
        return categoryService.selectAll();
    }

}
