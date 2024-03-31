package com.example.user.service.impl;

import com.example.user.dto.UpdateStateDTO;
import com.example.user.vo.RE;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.example.user.po.Goodsdetail;
import com.example.user.dao.GoodsdetailMapper;
import com.example.user.service.GoodsdetailService;
@Service
public class GoodsdetailServiceImpl implements GoodsdetailService{

    @Resource
    private GoodsdetailMapper goodsdetailMapper;

    @Override
    public RE deleteByPrimaryKey(Integer id) {
//        UpdateStateDTO updateStateDTO = new UpdateStateDTO();
//        updateStateDTO.setId(id);
//        updateStateDTO.setState("已下架");
        if (goodsdetailMapper.deleteByPrimaryKey(id) != 0){
            return RE.ok();
        }
        return RE.error();
    }

    @Override
    public int insert(Goodsdetail record) {
        return goodsdetailMapper.insert(record);
    }

    @Override
    public int insertSelective(Goodsdetail record) {
        return goodsdetailMapper.insertSelective(record);
    }

    @Override
    public Goodsdetail selectByPrimaryKey(Integer id) {
        return goodsdetailMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Goodsdetail record) {
        return goodsdetailMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public RE updateByPrimaryKey(Goodsdetail record) {
        if (goodsdetailMapper.updateByPrimaryKey(record) != 0){
            return RE.ok();
        }
        return RE.error();
    }

}
