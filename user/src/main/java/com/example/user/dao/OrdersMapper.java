package com.example.user.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.user.dto.CreateOrderDTO;
import com.example.user.dto.UpdateOrderAddressDTO;
import com.example.user.dto.UpdateOrderStatusDTO;
import com.example.user.po.Address;
import com.example.user.po.Orders;
import com.example.user.vo.OrderVO;
import com.example.user.vo.PagePara;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

@Mapper
public interface OrdersMapper extends BaseMapper {
    /**
     * delete by primary key
     * @param id primaryKey
     * @return deleteCount
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * insert record to table
     * @param record the record
     * @return insert count
     */
    int insert(Orders record);

    /**
     * insert record to table selective
     * @param createOrderDTO the record
     * @return insert count
     */
    int insertSelective(CreateOrderDTO createOrderDTO);

    /**
     * select by primary key
     * @param id primary key
     * @return object by primary key
     */
    Orders selectByPrimaryKey(Integer id);

    /**
     * update record selective
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(Orders record);

    /**
     * update record
     * @param
     * @return update count
     */
    int updateByPrimaryKey(UpdateOrderAddressDTO updateOrderAddressDTO);

//根据主键查询订单信息
    OrderVO findById(Integer id);

//    根据主键查询订单状态
    String findStatusById(Integer id);

//    查询订单地址
    String findAddressById(Integer addressId);

//    分页查询用户订单
    IPage<OrderVO> findMyOrderByCustomerId(@Param("id")Long id,Page<PagePara> page, @Param("par")PagePara pagePara);

//    分页查询商家的订单
    IPage<OrderVO> findMyOrderByBusinessId(@Param("id")Long id,Page<PagePara> page, @Param("par")PagePara pagePara);

//   修改订单状态
    int updateOrderStatus(UpdateOrderStatusDTO updateOrderStatusDTO);

}
