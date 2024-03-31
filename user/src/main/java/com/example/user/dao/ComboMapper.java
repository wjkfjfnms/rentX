package com.example.user.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.user.dto.UpdateStateDTO;
import com.example.user.po.Combo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ComboMapper extends BaseMapper {

//    下架套餐
    int updateCombo(UpdateStateDTO updateStateDTO);

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
    int insert(Combo record);

    /**
     * insert record to table selective
     * @param record the record
     * @return insert count
     */
    int insertSelective(Combo record);

    /**
     * select by primary key
     * @param id primary key
     * @return object by primary key
     */
    Combo selectByPrimaryKey(Integer id);

    /**
     * update record selective
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(Combo record);

    /**
     * update record
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(Combo record);
}
