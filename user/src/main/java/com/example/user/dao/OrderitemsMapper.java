package com.example.user.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.user.po.Orderitems;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderitemsMapper extends BaseMapper {
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
    int insert(Orderitems record);

    /**
     * insert record to table selective
     * @param record the record
     * @return insert count
     */
    int insertSelective(Orderitems record);

    /**
     * select by primary key
     * @param id primary key
     * @return object by primary key
     */
    Orderitems selectByPrimaryKey(Integer id);

    /**
     * update record selective
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(Orderitems record);

    /**
     * update record
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(Orderitems record);
}