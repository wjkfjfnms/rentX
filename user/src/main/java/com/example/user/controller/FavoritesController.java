package com.example.user.controller;

import com.example.user.po.Favorites;
import com.example.user.service.FavoritesService;
import com.example.user.vo.PagePara;
import com.example.user.vo.RE;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "/api/user")
@Api(tags = {"收藏接口"})
public class FavoritesController {

    @Autowired
    private FavoritesService favoritesService;

    @ApiOperation(value = "收藏")
    @PostMapping("/insertSelective")
    public RE insertSelective(@Validated @RequestBody Favorites favorites){
        return favoritesService.insertSelective(favorites);
    }

    @ApiOperation(value = "删除收藏")
    @DeleteMapping("/deleteByPrimaryKey")
    public RE deleteByPrimaryKey(Integer id){
        return favoritesService.deleteByPrimaryKey(id);
    }

    @ApiOperation(value = "分页查询收藏")
    @GetMapping("/selectByUserId")
    public RE selectByUserId(Integer userId, PagePara pagePara){
        return favoritesService.selectByUserId(userId,pagePara);
    }

}
