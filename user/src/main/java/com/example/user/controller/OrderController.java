package com.example.user.controller;


import com.example.user.dto.CreateOrderDTO;
import com.example.user.dto.UpdateOrderAddressDTO;
import com.example.user.dto.UpdateOrderStatusDTO;
import com.example.user.service.OrdersService;
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
@RequestMapping(value = "/api/order")
@Api(tags = {"订单接口"})
public class OrderController {

    @Autowired
    OrdersService ordersService;


    @ApiOperation(value = "创建订单")
    @PostMapping("/createOrder")
    public RE createOrder(@Validated @RequestBody CreateOrderDTO createOrderDTO){
        return ordersService.insertSelective(createOrderDTO);
    }

    @ApiOperation(value = "查询订单详情")
    @GetMapping("/findOrderDetail")
    public RE findOrderDetail(Integer id){
        return ordersService.selectByPrimaryKey(id);
    }

    @ApiOperation(value = "分页查询我的订单")
    @GetMapping("/findMyOrder")
    public RE findMyOrder(PagePara pagePara){
        return ordersService.findMyOrder(pagePara);
    }

    @ApiOperation(value = "用户修改订单地址")
    @PutMapping("/updateOrderAddress")
    public RE updateOrderAddress(@Validated @RequestBody UpdateOrderAddressDTO updateOrderAddressDTO){
        return ordersService.updateByPrimaryKey(updateOrderAddressDTO);
    }

    @ApiOperation(value = "修改订单状态")
    @PutMapping("/updateOrderStatus")
    public RE updateOrderStatus(@Validated @RequestBody UpdateOrderStatusDTO updateOrderStatusDTO){
        return ordersService.updateOrderStatus(updateOrderStatusDTO);
    }

}
