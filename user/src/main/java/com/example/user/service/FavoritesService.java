package com.example.user.service;

import com.example.user.po.Favorites;
import com.example.user.vo.PagePara;
import com.example.user.vo.RE;

public interface FavoritesService{

//    分页查询收藏
    RE selectByUserId(Integer userId,PagePara pagePara);

//删除收藏
    RE deleteByPrimaryKey(Integer id);

    int insert(Favorites record);

//    加入收藏
    RE insertSelective(Favorites favorites);

    Favorites selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Favorites record);

    int updateByPrimaryKey(Favorites record);

}
