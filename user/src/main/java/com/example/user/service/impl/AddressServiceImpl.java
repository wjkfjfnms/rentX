package com.example.user.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.user.service.CommonService;
import com.example.user.util.PageResultS;
import com.example.user.vo.FavoritesVO;
import com.example.user.vo.PagePara;
import com.example.user.vo.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.example.user.dao.AddressMapper;
import com.example.user.po.Address;
import com.example.user.service.AddressService;
@Service
public class AddressServiceImpl implements AddressService{

    @Resource
    private AddressMapper addressMapper;

    @Autowired
    CommonService commonService;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return addressMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Address record) {
        return addressMapper.insert(record);
    }

    @Override
    public RE insertSelective(Address address) {
//        获取用户id
        Long id = commonService.getUsersDetails().getId();

        if (address != null){
//            添加地址
            address.setUserid(id);
            int re = addressMapper.insertSelective(address);
            if (re != 0){
//            返回新增的地址信息
                Address result = addressMapper.selectByPrimaryKey(address.getId());
                return RE.ok().data("result",result);
            }else {
                return RE.error();
            }
        }else {
            return RE.error().message("参数为空！");
        }
    }

    @Override
    public Address selectByPrimaryKey(Integer id) {
        return addressMapper.selectByPrimaryKey(id);
    }

    @Override
    public RE updateByPrimaryKeySelective(Address address) {
        if (address == null){
            return RE.error().message("参数为空！");
        }
        int re= addressMapper.updateByPrimaryKeySelective(address);
        if (re == 0){
            return RE.error().message("修改失败！");
        }
        Address result = addressMapper.selectByPrimaryKey(address.getId());
        return RE.ok().data("result",result);
    }

    @Override
    public RE updateByPrimaryKey(Integer id) {
        if (id == null){
            return RE.error().message("参数为空！");
        }
        int re= addressMapper.updateByPrimaryKey(id);
        if (re == 0){
            return RE.error().message("删除失败！");
        }
        return RE.ok();
    }

    @Override
    public RE findAll(PagePara pagePara) {
        Long userId = commonService.getUsersDetails().getId();
        // 创建 Page 对象，指定当前页和每页显示数量
        Page<PagePara> page = new Page<>(pagePara.getNowPage() == null ? 1 : pagePara.getNowPage(), pagePara.getOnePageCount() == null ? 3 : pagePara.getOnePageCount());
        IPage<Address> queryResult =addressMapper.findAll(userId,page, pagePara);
        // 根据查询结果构建 PagePara 对象，包括当前页、每页数量、总记录数和总页数
        PagePara pageResult = new PagePara(queryResult.getCurrent(), queryResult.getSize(), queryResult.getTotal(), queryResult.getPages());
        // 构建 PageResultS 对象，设置查询结果列表和分页信息
        PageResultS<Address> result = new PageResultS<>();
        result.setList(queryResult.getRecords());
        result.setPage(pageResult);
        if (result != null){
            return RE.ok().data("List",result);
        }else {
            return RE.error();
        }
    }

}
