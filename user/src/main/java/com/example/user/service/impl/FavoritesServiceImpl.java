package com.example.user.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.user.dao.GoodsMapper;
import com.example.user.po.Users;
import com.example.user.service.CommonService;
import com.example.user.util.PageResultS;
import com.example.user.vo.FavoritesVO;
import com.example.user.vo.PagePara;
import com.example.user.vo.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.example.user.dao.FavoritesMapper;
import com.example.user.po.Favorites;
import com.example.user.service.FavoritesService;
@Service
public class FavoritesServiceImpl implements FavoritesService{

    @Resource
    private FavoritesMapper favoritesMapper;

    @Autowired
    CommonService commonService;

    @Autowired
    GoodsMapper goodsMapper;

    @Override
    public RE selectByUserId(PagePara pagePara) {
        //        从token中获取用户id
        Users users = commonService.getUsersDetails();
        // 创建 Page 对象，指定当前页和每页显示数量
        Page<PagePara> page = new Page<>(pagePara.getNowPage() == null ? 1 : pagePara.getNowPage(), pagePara.getOnePageCount() == null ? 3 : pagePara.getOnePageCount());
        IPage<FavoritesVO> queryResult =favoritesMapper.selectByUserId(users.getId(),page, pagePara);
        // 根据查询结果构建 PagePara 对象，包括当前页、每页数量、总记录数和总页数
        PagePara pageResult = new PagePara(queryResult.getCurrent(), queryResult.getSize(), queryResult.getTotal(), queryResult.getPages());
        // 构建 PageResultS 对象，设置查询结果列表和分页信息
        PageResultS<FavoritesVO> result = new PageResultS<>();
        result.setList(queryResult.getRecords());
        result.setPage(pageResult);
        if (result != null){
            return RE.ok().data("List",result);
        }else {
            return RE.error();
        }
    }

    @Override
    public RE deleteByPrimaryKey(Integer id) {
        if (id != null){
            int re = favoritesMapper.deleteByPrimaryKey(id);
            if (re != 0){
                return RE.ok();
            }else {
                return RE.error();
            }
        }else {
            return RE.error().message("参数为空！");
        }
    }

    @Override
    public int insert(Favorites record) {
        return favoritesMapper.insert(record);
    }

    @Override
    public RE insertSelective(Favorites favorites) {
        if (favorites != null){
            int re = favoritesMapper.insertSelective(favorites);
            if (re !=0){
//                收藏量+1
                int shoucang = goodsMapper.getFavorites(favorites.getId()).getFavorites()+1;
                goodsMapper.updateFavorites(favorites.getId(),shoucang);
                return RE.ok();
            }else {
                return RE.error();
            }
        }else {
            return RE.error().message("参数为空！");
        }
    }

    @Override
    public Favorites selectByPrimaryKey(Integer id) {
        return favoritesMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Favorites record) {
        return favoritesMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Favorites record) {
        return favoritesMapper.updateByPrimaryKey(record);
    }

}
