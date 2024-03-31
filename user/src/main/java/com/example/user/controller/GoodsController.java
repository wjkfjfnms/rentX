package com.example.user.controller;

import com.example.user.dto.UpdateGoodsDTO;
import com.example.user.dto.UploadGoodsDTO;
import com.example.user.service.GoodsService;
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
@RequestMapping(value = "/api/goods")
@Api(tags = {"商品接口"})
public class GoodsController {
    @Autowired
    GoodsService goodsService;

    @ApiOperation(value = "查询某个商品详情接口")
    @GetMapping("/selectByPrimaryKey")
    public RE selectByPrimaryKey(int id){
        return goodsService.selectByPrimaryKey(id);
    }

    @ApiOperation(value = "新增商品接口")
    @PostMapping("/addGoods")
    public RE addGoods(@Validated @RequestBody UploadGoodsDTO uploadGoodsDTO){
        return goodsService.insertSelective(uploadGoodsDTO);
    }

    @ApiOperation(value = "修改商品信息接口")
    @PutMapping("/updateGoods")
    public RE updateByPrimaryKeySelective(@Validated @RequestBody UpdateGoodsDTO updateGoodsDTO){
        return goodsService.updateByPrimaryKeySelective(updateGoodsDTO);
    }


    // 下架商品
    @ApiOperation(value = "下架商品接口")
    @PutMapping("/downGoods")
    public RE downGoods(Integer id){
        return goodsService.downGoods(id);
    }

    //    商品重新上架
    @ApiOperation(value = "商品重新上架")
    @PutMapping("/upGoods")
    public RE upGoods(Integer id){
        return goodsService.upGoods(id);
    }

    //    停售商品
    @ApiOperation(value = "停售商品")
    @PutMapping("/stopGoods")
    public RE stopGoods(Integer id){
        return goodsService.stopGoods(id);
    }

    @ApiOperation(value = "商家查询他的全部商品")
    @GetMapping("/findMyGoods")
    public RE findMyGoods( PagePara pagePara){
        return goodsService.findMyGoods(pagePara);
    }

}
