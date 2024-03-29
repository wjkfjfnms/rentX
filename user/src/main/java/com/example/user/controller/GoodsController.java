package com.example.user.controller;

import com.example.user.dto.GoodsDetailSelectDTO;
import com.example.user.service.GoodsService;
import com.example.user.vo.GoodsVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/api/goods")
@Api(tags = {"商品接口"})
public class GoodsController {

    @Autowired
    GoodsService goodsService;

    @ApiOperation(value = "商品详情接口")
    @PostMapping("/selectByPrimaryKey")
    public GoodsVO selectByPrimaryKey(int id){
        return goodsService.selectByPrimaryKey(id);
    }
}
