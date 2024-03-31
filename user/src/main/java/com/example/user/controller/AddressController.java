package com.example.user.controller;

import com.example.user.po.Address;
import com.example.user.service.AddressService;
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
@Api(tags = {"我的收货地址接口"})
public class AddressController {


    @Autowired
    AddressService addressService;

    @ApiOperation(value = "添加收货地址")
    @PostMapping("/insertAddress")
    public RE insertSelective(@Validated @RequestBody Address address){
        return addressService.insertSelective(address);
    }

    @ApiOperation(value = "查询某个收货地址信息")
    @GetMapping("/selectByPrimaryKey")
    public RE selectByPrimaryKey(Integer id){
        Address address=addressService.selectByPrimaryKey(id);
        if (address != null){
            return RE.ok().data("address",address);
        }else {
            return RE.error();
        }
    }

    @ApiOperation(value = "修改收货地址信息")
    @PutMapping("/updateByPrimaryKeySelective")
    public RE updateByPrimaryKeySelective(@Validated @RequestBody Address address){
        return addressService.updateByPrimaryKeySelective(address);
    }

    @ApiOperation(value = "删除收货地址信息")
    @PutMapping("/updateByPrimaryKey")
    public RE updateByPrimaryKey(Integer id){
        return addressService.updateByPrimaryKey(id);
    }

    @ApiOperation(value = "分页查询地址")
    @GetMapping("/findAll")
    public RE findAll(PagePara pagePara){
        return addressService.findAll(pagePara);
    }
}
