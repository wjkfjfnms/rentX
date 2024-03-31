package com.example.user.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.user.po.Address;
import com.example.user.vo.PagePara;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

@Mapper
public interface AddressMapper extends BaseMapper {
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
    int insert(Address record);

    /**
     * 添加收货地址
     * @param record the record
     * @return insert count
     */
    int insertSelective(Address record);

    /**
     * 根据主键返回收货地址信息
     * @param id primary key
     * @return object by primary key
     */
    Address selectByPrimaryKey(Integer id);

    /**
     * 修改收货地址信息
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(Address record);

    /**
     * 删除收货地址
     * @param id the updated record
     * @return update count
     */
    int updateByPrimaryKey(Integer id);

    /**
     * 分页查询全部地址信息
     * @param page
     * @param pagePara
     * @return
     */
    IPage<Address> findAll(@Param("userId") Integer userId, Page<PagePara> page, @Param("par") PagePara pagePara);
}
