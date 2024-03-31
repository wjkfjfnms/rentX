package com.example.user.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.user.dto.UpdateStateDTO;
import com.example.user.po.Goodsdetail;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GoodsdetailMapper extends BaseMapper {

    int upByPrimaryKey(UpdateStateDTO updateStateDTO);

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
    int insert(Goodsdetail record);

    /**
     * insert record to table selective
     * @param record the record
     * @return insert count
     */
    int insertSelective(Goodsdetail record);

    /**
     * select by primary key
     * @param id primary key
     * @return object by primary key
     */
    Goodsdetail selectByPrimaryKey(Integer id);

    /**
     * update record selective
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(Goodsdetail record);

    /**
     * update record
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(Goodsdetail record);
}
