package cn.lvhaosir.service.impl;


import cn.lvhaosir.entity.CarsRepair;
import cn.lvhaosir.mapper.CarsRepairMapper;
import cn.lvhaosir.paramater.CarsRepairParameter;
import cn.lvhaosir.service.CarsRepairService;
import cn.lvhaosir.utils.PageData;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

@Service
public class CarsRepairServiceImpl implements CarsRepairService {


    @Autowired
    private CarsRepairMapper carsRepairMapper;


    @Override
    public Integer add(CarsRepair carsRepair) {
        carsRepair.setCreateTime(new Date());
        carsRepair.setUpdateTime(new Date());
        return carsRepairMapper.insert(carsRepair);
    }

    @Override
    public Integer delete(Integer id) {
        return carsRepairMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Integer update(CarsRepair carsRepair) {
        carsRepair.setUpdateTime(new Date());
        Example example = new Example(CarsRepair.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("carsRepairId", carsRepair.getCarsRepairId());
        return carsRepairMapper.updateByExampleSelective(carsRepair, example);
    }

    @Override
    public CarsRepair selectCarsRepairById(Integer id) {
        CarsRepair users = new CarsRepair();
        users.setCarsRepairId(id);
        return carsRepairMapper.selectOne(users);
    }

    @Override
    public PageData<CarsRepair> queryLikeCarsRepair(CarsRepair carsRepair) {
        PageHelper.startPage(carsRepair.getPageNum(), carsRepair.getPageSize());
        List<CarsRepair> list = carsRepairMapper.select(carsRepair);
        PageInfo<CarsRepair> pageInfo = PageInfo.of(list);
        return new PageData<>(list, pageInfo.getTotal());
    }

    @Override
    public PageData<CarsRepair> queryAllCarsRepair(CarsRepairParameter carsRepairParameter) {
        PageHelper.startPage(carsRepairParameter.getPageNum(), carsRepairParameter.getPageSize());
        Example example = new Example(CarsRepair.class);
        Example.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(carsRepairParameter.getUserName())) {
            criteria.andEqualTo("userName", carsRepairParameter.getUserName());
        }
        if (StringUtils.isNotBlank(carsRepairParameter.getPhone())) {
            criteria.andEqualTo("phone", carsRepairParameter.getPhone());
        }
        if (StringUtils.isNotBlank(carsRepairParameter.getCarNumber())) {
            criteria.andEqualTo("carNumber", carsRepairParameter.getCarNumber());
        }
        if (carsRepairParameter.getCreateTimeArray() != null) {
//            criteria.andEqualTo("createTime", carsRepairParameter.getCreateTimeArray()[0]);
            criteria.andBetween("createTime",
                    carsRepairParameter.getCreateTimeArray()[0],
                    carsRepairParameter.getCreateTimeArray()[1]);
        }
        List<CarsRepair> list = carsRepairMapper.selectByExample(example);
        PageInfo<CarsRepair> pageInfo = PageInfo.of(list);
        return new PageData<>(list, pageInfo.getTotal());
    }
}
