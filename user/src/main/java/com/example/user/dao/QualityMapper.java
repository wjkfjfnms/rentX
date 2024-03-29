package com.example.user.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.user.po.Quality;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QualityMapper extends BaseMapper {
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
    int insert(Quality record);

    /**
     * insert record to table selective
     * @param record the record
     * @return insert count
     */
    int insertSelective(Quality record);

    /**
     * select by primary key
     * @param id primary key
     * @return object by primary key
     */
    Quality selectByPrimaryKey(Integer id);

    /**
     * update record selective
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(Quality record);

    /**
     * update record
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(Quality record);
}