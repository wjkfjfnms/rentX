package com.example.user.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.user.dto.UpdateGoodsDTO;
import com.example.user.dto.UpdateStateDTO;
import com.example.user.dto.UploadGoodsDTO;
import com.example.user.po.Goods;
import com.example.user.vo.GoodsVO;
import com.example.user.vo.PagePara;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Mapper
public interface GoodsMapper extends BaseMapper<Goods> {

//    获取收藏量
    Goods getFavorites(Integer id);

//    收藏+1
    int updateFavorites(Integer id,Integer shoucang);

//    最近上新
    List<Goods> RecentlyNew();

//    热销推荐
    List<Goods> hostGoods();

//    分页查询
    IPage<Goods> findMyGoods(Long uid, Page<PagePara> page, @Param("par")PagePara pagePara);

//    下架商品
    int updateState(UpdateStateDTO updateStateDTO);

//    查找同名商品
    GoodsVO selectByGoodsName(UploadGoodsDTO uploadGoodsDTO);

    //    根据分类查询商品
    IPage<Goods> selectByCategory(Integer id, Page<PagePara> page, @Param("par")PagePara pagePara);

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
    @Override
    int insert(Goods record);

    /**
     * insert record to table selective
     * @param uploadGoodsDTO the record
     * @return insert count
     */
    int insertSelective(UploadGoodsDTO uploadGoodsDTO);

    /**
     * select by primary key
     * @param id primary key
     * @return object by primary key
     */
    GoodsVO selectByPrimaryKey(Integer id);

    /**
     * update record selective
     * @param updateGoodsDTO the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(UpdateGoodsDTO updateGoodsDTO);

    /**
     * update record
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(Goods record);

    IPage<Goods> searchGoods(String keyword,Page<PagePara> page, @Param("par")PagePara pagePara);

    IPage<Goods> UsersearchGoods(String keyword,Page<PagePara> page, @Param("par")PagePara pagePara);

    IPage<Goods> userSelectAllgoods(Page<PagePara> page, @Param("par")PagePara pagePara);

//    Integer findFavorites(Integer id);
//
//    int addFavorites(Integer id,Integer f);
}
