package com.example.user.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.user.dao.*;
import com.example.user.dto.UpdateGoodsDTO;
import com.example.user.dto.UpdateStateDTO;
import com.example.user.dto.UploadGoodsDTO;
import com.example.user.po.*;
import com.example.user.service.CommonService;
import com.example.user.service.UploadImageService;
import com.example.user.util.PageResultS;
import com.example.user.vo.GoodsVO;
import com.example.user.vo.PagePara;
import com.example.user.vo.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

import com.example.user.service.GoodsService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService{

    @Resource
    private GoodsMapper goodsMapper;

    @Autowired
    CommonService commonService;

    @Autowired
    CategoryMapper categoryMapper;

    @Autowired
    ComboMapper comboMapper;

    @Autowired
    QualityMapper qualityMapper;

    @Autowired
    GoodsdetailMapper goodsdetailMapper;

    @Autowired
    UploadImageService uploadImageService;



    @Override
    public RE RecentlyNew() {
        Goods goods = goodsMapper.RecentlyNew();
        if (goods != null){
            return RE.ok().data("result",goods);
        }
        return RE.error();
    }

    @Override
    public RE hostGoods() {
        Goods goods = goodsMapper.hostGoods();
        if (goods != null){
            return RE.ok().data("result",goods);
        }
        return RE.error();
    }

    @Override
    public RE findMyGoods(PagePara pagePara) {
//        从token中获取用户id
        Users users = commonService.getUsersDetails();
        // 创建 Page 对象，指定当前页和每页显示数量
        Page<PagePara> page = new Page<>(pagePara.getNowPage() == null ? 1 : pagePara.getNowPage(), pagePara.getOnePageCount() == null ? 3 : pagePara.getOnePageCount());
        IPage<Goods> queryResult =goodsMapper.findMyGoods(users.getId(),page, pagePara);
        // 根据查询结果构建 PagePara 对象，包括当前页、每页数量、总记录数和总页数
        PagePara pageResult = new PagePara(queryResult.getCurrent(), queryResult.getSize(), queryResult.getTotal(), queryResult.getPages());
        // 构建 PageResultS 对象，设置查询结果列表和分页信息
        PageResultS<Goods> result = new PageResultS<>();
        result.setList(queryResult.getRecords());
        result.setPage(pageResult);
        if (result != null){
            return RE.ok().data("List",result);
        }else {
            return RE.error();
        }
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return goodsMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Goods record) {
        return goodsMapper.insert(record);
    }

    @Override
    public RE insertSelective(UploadGoodsDTO uploadGoodsDTO, List<MultipartFile> multipartFileList, MultipartFile multipartFile) {
        if (uploadGoodsDTO == null){
            return RE.error().message("参数为空！");
        }
//        获取商家id
        Long userId = commonService.getUsersDetails().getId();
//        获取类别
        Integer categoryId = categoryMapper.selectByCategory(uploadGoodsDTO.getCategory()).getId();
        if (categoryId == null){
            return RE.error().message("无法找到该类别,请输入有效类别！");
        }
        uploadGoodsDTO.setUserId(Math.toIntExact(userId));
        uploadGoodsDTO.setCategoryId(categoryId);
//        查看商品表是否已存在同名商品
        if (goodsMapper.selectByGoodsName(uploadGoodsDTO) != null){
            return RE.error().message("您的商店已存在同名商品！");
        }
//        在商品表添加信息
        uploadGoodsDTO.setGoodspicture(uploadImageService.upload(multipartFile).get("name"));
        int re = goodsMapper.insertSelective(uploadGoodsDTO);
        if (re == 0){
            return RE.error().message("商品添加失败,请重新上传！");
        }
//        检查传入的套餐列表是否为空
        // 检查传入的套餐列表是否为空
        if(uploadGoodsDTO.getComboList() == null || uploadGoodsDTO.getComboList().isEmpty()) {
            return RE.error().message("套餐列表不能为空");
        }
//        在套餐表添加信息
        for(Combo combo : uploadGoodsDTO.getComboList()) {
            // 调用 ComboMapper 中的方法将套餐信息添加到数据库中
            combo.setGoodsid(uploadGoodsDTO.getId());
            int ree = comboMapper.insertSelective(combo);
            if (ree == 0){
                return RE.error().message("套餐、成色、详情图片信息上传失败，请重新上传！");
            }
        }
        // 检查传入的列表是否为空
        if(uploadGoodsDTO.getQualityList() == null || uploadGoodsDTO.getQualityList().isEmpty()) {
            return RE.error().message("成色列表不能为空");
        }
//        在成色表添加信息
        for (Quality quality : uploadGoodsDTO.getQualityList()){
            quality.setGoodsid(uploadGoodsDTO.getId());
            if (qualityMapper.insertSelective(quality) == 0){
                return RE.error().message("成色、详情图片信息上传失败，请重新上传！");
            }
        }
//        在详情表添加信息
        if(multipartFileList == null || multipartFileList.isEmpty()) {
            return RE.error().message("详情图片列表为空");
        }
        Goodsdetail goodsDetail = new Goodsdetail();
        goodsDetail.setGoodsid(uploadGoodsDTO.getId());
        for (MultipartFile MF : multipartFileList){
//            获取图片地址
            goodsDetail.setAddress(uploadImageService.upload(MF).get("name"));
            if (goodsdetailMapper.insertSelective(goodsDetail) == 0){
                return RE.error().message("详情图片信息上传失败，请重新上传！");
            }
        }
//        返回上传后的商品信息
        GoodsVO upload = goodsMapper.selectByPrimaryKey(uploadGoodsDTO.getId());
        return RE.ok().data("result",upload);
    }

    @Override
    public RE selectByPrimaryKey(Integer id) {
        GoodsVO goodsVO = goodsMapper.selectByPrimaryKey(id);
        if (goodsVO != null){
            return RE.ok().data("result",goodsVO);
        }else {
            return RE.error().message("商品不存在");
        }
    }

    @Override
    public RE updateByPrimaryKeySelective(UpdateGoodsDTO updateGoodsDTO) {
        if (goodsMapper.updateByPrimaryKeySelective(updateGoodsDTO) != 0){
            GoodsVO goods = goodsMapper.selectByPrimaryKey(updateGoodsDTO.getId());
            return RE.ok().data("result",goods);
        }else {
            return RE.error();
        }
    }

    @Override
    public RE updateState(Integer id, String state) {
//        在goods表修改该商品状态
        UpdateStateDTO updateStateDTO = new UpdateStateDTO();
        updateStateDTO.setId(id);
        updateStateDTO.setState(state);
        if (goodsMapper.updateState(updateStateDTO) != 0){
//            在套餐表修改该商品状态
            if (comboMapper.updateCombo(updateStateDTO) != 0){
//            成色修改该商品状态
                if (qualityMapper.updateQuality(updateStateDTO) != 0){
                    if (goodsdetailMapper.upByPrimaryKey(updateStateDTO) != 0){
                        return RE.ok();
                    }
                }
            }


        }
        return RE.error();
    }

    @Override
    public RE downGoods(Integer id) {
//        在goods表下架该商品
        String state = "已下架";
        UpdateStateDTO updateStateDTO = new UpdateStateDTO();
        updateStateDTO.setId(id);
        updateStateDTO.setState(state);
        return this.updateState(id,state);
    }

    @Override
    public RE upGoods(Integer id) {
        String state = "在售";
        UpdateStateDTO updateStateDTO = new UpdateStateDTO();
        updateStateDTO.setId(id);
        updateStateDTO.setState(state);
        return this.updateState(id,state);
    }

    @Override
    public RE stopGoods(Integer id) {
        String state = "停售";
        UpdateStateDTO updateStateDTO = new UpdateStateDTO();
        updateStateDTO.setId(id);
        updateStateDTO.setState(state);
        return this.updateState(id,state);
    }


    @Override
    public int updateByPrimaryKey(Goods record) {
        return goodsMapper.updateByPrimaryKey(record);
    }

}
