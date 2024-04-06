package com.example.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.user.dto.UpdateGoodsDTO;
import com.example.user.dto.UploadGoodsDTO;
import com.example.user.po.Goods;
import com.example.user.po.Users;
import com.example.user.vo.GoodsVO;
import com.example.user.vo.PagePara;
import com.example.user.vo.RE;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface GoodsService extends IService<Goods> {

//    最近上新
    RE RecentlyNew();

//    热销推荐
    RE hostGoods();

//    分页查询
    RE findMyGoods(PagePara pagePara);

    int deleteByPrimaryKey(Integer id);

    int insert(Goods record);

//    上传商品信息
//    RE insertSelective(UploadGoodsDTO uploadGoodsDTO,MultipartFile multipartFileDetail, MultipartFile multipartFile);
    RE insertSelective(UploadGoodsDTO uploadGoodsDTO);

//    根据主键查找商品信息
    RE selectByPrimaryKey(Integer id);

//    修改商品信息
    RE updateByPrimaryKeySelective(UpdateGoodsDTO updateGoodsDTO);

    //   修改商品状态
    RE updateState(Integer id, String state);

    // 下架商品
    RE downGoods(Integer id);

//    商品重新上架
    RE upGoods(Integer id);

//    停售商品
    RE stopGoods(Integer id);

    int updateByPrimaryKey(Goods record);

//    模糊查询商品
    RE searchGoods(PagePara pagePara,String keyword);

//    根据类别id查找商品
    RE selectByCategory(Integer id,PagePara pagePara);

}
