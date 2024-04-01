package com.example.user.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.user.dao.GoodsMapper;
import com.example.user.dao.OrderitemsMapper;
import com.example.user.dto.CreateOrderDTO;
import com.example.user.dto.UpdateOrderAddressDTO;
import com.example.user.po.Address;
import com.example.user.service.CommonService;
import com.example.user.service.UsersService;
import com.example.user.util.PageResultS;
import com.example.user.vo.GoodsVO;
import com.example.user.vo.OrderVO;
import com.example.user.vo.PagePara;
import com.example.user.vo.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.example.user.dao.OrdersMapper;
import com.example.user.po.Orders;
import com.example.user.service.OrdersService;

import java.util.concurrent.ThreadLocalRandom;

@Service
public class OrdersServiceImpl implements OrdersService{

    @Resource
    private OrdersMapper ordersMapper;

    @Autowired
    CommonService commonService;

    @Autowired
    GoodsMapper goodsMapper;

    @Autowired
    OrderitemsMapper orderitemsMapper;

    @Autowired
    UsersService usersService;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return ordersMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Orders record) {
        return ordersMapper.insert(record);
    }

    @Override
    public RE insertSelective(CreateOrderDTO createOrderDTO) {
        if (createOrderDTO == null){
            return RE.error().message("参数为空");
        }
//        获取客户id
        createOrderDTO.setCustomerid(commonService.getUsersDetails().getId());
//        获取商家id
        GoodsVO goodsVO = goodsMapper.selectByPrimaryKey(createOrderDTO.getGoodsid());
        createOrderDTO.setBusinessid(goodsVO.getUserId());
//        设置单项租金
        createOrderDTO.setItemprice(goodsVO.getPrice());
//        设置单项押金
        createOrderDTO.setItemdeposit(goodsVO.getPrice() * 10);
//        设置总租金
        createOrderDTO.setPrice(createOrderDTO.getItemprice() * createOrderDTO.getNumber() * createOrderDTO.getLeasetime());
//          设置总押金
        createOrderDTO.setDeposit(createOrderDTO.getItemdeposit() * createOrderDTO.getNumber());
//        设置订单状态
        createOrderDTO.setState("未付款");
//        生成订单编号
        StringBuilder randomNum = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            randomNum.append(ThreadLocalRandom.current().nextInt(10));
        }
        createOrderDTO.setOrdernum(randomNum.toString());
        if (ordersMapper.insertSelective(createOrderDTO) != 0){
            if (orderitemsMapper.insertSelective(createOrderDTO) != 0){
//                查询订单信息
                OrderVO result = ordersMapper.findById(createOrderDTO.getId());
                return RE.ok().data("result",result);
            }
            return RE.error().message("订单创建失败，请重新创建！");
        }
        return RE.error().message("订单创建失败，请重新创建！");

    }

    @Override
    public RE selectByPrimaryKey(Integer id) {
        OrderVO result = ordersMapper.findById(id);
        if (result != null){
//            result.setAddress(ordersMapper.findAddressById(result.getAddressid()));
            return RE.ok().data("result",result);
        }
        return RE.error().message("没有该订单！");
    }

    @Override
    public RE findMyOrder(PagePara pagePara) {
//        获取用户id
        Long id = commonService.getUsersDetails().getId();
//        判断用户是商家还是客户
        if (usersService.selectByPrimaryKey(id).getRole().equals("MERCHANTS")){
//            查询商家订单
            // 创建 Page 对象，指定当前页和每页显示数量
            Page<PagePara> page = new Page<>(pagePara.getNowPage() == null ? 1 : pagePara.getNowPage(), pagePara.getOnePageCount() == null ? 3 : pagePara.getOnePageCount());
            IPage<OrderVO> queryResult =ordersMapper.findMyOrderByBusinessId(id,page, pagePara);
            // 根据查询结果构建 PagePara 对象，包括当前页、每页数量、总记录数和总页数
            PagePara pageResult = new PagePara(queryResult.getCurrent(), queryResult.getSize(), queryResult.getTotal(), queryResult.getPages());
            // 构建 PageResultS 对象，设置查询结果列表和分页信息
            PageResultS<OrderVO> result = new PageResultS<>();
            result.setList(queryResult.getRecords());
            result.setPage(pageResult);
            if (result != null){
                return RE.ok().data("result",result);
            }else {
                return RE.error();
            }
        }else if (usersService.selectByPrimaryKey(id).getRole().equals("USER")){
//            查询用户订单
            // 创建 Page 对象，指定当前页和每页显示数量
            Page<PagePara> page = new Page<>(pagePara.getNowPage() == null ? 1 : pagePara.getNowPage(), pagePara.getOnePageCount() == null ? 3 : pagePara.getOnePageCount());
            IPage<OrderVO> queryResult =ordersMapper.findMyOrderByCustomerId(id,page, pagePara);
            // 根据查询结果构建 PagePara 对象，包括当前页、每页数量、总记录数和总页数
            PagePara pageResult = new PagePara(queryResult.getCurrent(), queryResult.getSize(), queryResult.getTotal(), queryResult.getPages());
            // 构建 PageResultS 对象，设置查询结果列表和分页信息
            PageResultS<OrderVO> result = new PageResultS<>();
            result.setList(queryResult.getRecords());
            result.setPage(pageResult);
            if (result != null){
                return RE.ok().data("result",result);
            }else {
                return RE.error();
            }
        }
//      查询
        return RE.error().message("没有您的订单！");
    }

    @Override
    public int updateByPrimaryKeySelective(Orders record) {
        return ordersMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public RE updateByPrimaryKey(UpdateOrderAddressDTO updateOrderAddressDTO) {
//        判断订单状态是否是
        String status = ordersMapper.findStatusById(updateOrderAddressDTO.getId());
        if (status.equals("未付款") || status.equals("已付款") || status.equals("待发货")){
            if (ordersMapper.updateByPrimaryKey(updateOrderAddressDTO) != 0){
                OrderVO orderVO = ordersMapper.findById(updateOrderAddressDTO.getId());
                return RE.ok().data("result",orderVO);
            }
        }
        return RE.error().message("订单已发货，无法修改地址！");
    }

}
