package com.example.user.service;

import com.example.user.dto.CreateOrderDTO;
import com.example.user.dto.UpdateOrderAddressDTO;
import com.example.user.dto.UpdateOrderStatusDTO;
import com.example.user.po.Address;
import com.example.user.po.Orders;
import com.example.user.vo.PagePara;
import com.example.user.vo.RE;

public interface OrdersService{


    int deleteByPrimaryKey(Integer id);

    int insert(Orders record);

//    创建订单
    RE insertSelective(CreateOrderDTO createOrderDTO);

//    查看订单详情
    RE selectByPrimaryKey(Integer id);

//    分页查询我的订单,返回orderitems表的id
    RE findMyOrder(PagePara pagePara);

    int updateByPrimaryKeySelective(Orders record);

//    修改订单地址信息
    RE updateByPrimaryKey(UpdateOrderAddressDTO updateOrderAddressDTO);

//    修改订单状态
    RE updateOrderStatus(UpdateOrderStatusDTO updateOrderStatusDTO);

}
