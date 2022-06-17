package cn.lvhaosir.service.impl;


import cn.lvhaosir.entity.CarsRepair;
import cn.lvhaosir.mapper.CarsRepairMapper;
import cn.lvhaosir.paramater.CarsRepairParameter;
import cn.lvhaosir.service.CarsRepairService;
import cn.lvhaosir.utils.DateUtils;
import cn.lvhaosir.utils.PageData;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CarsRepairServiceImpl implements CarsRepairService {


    @Autowired
    private CarsRepairMapper carsRepairMapper;


    @Override
    public Integer add(CarsRepair carsRepair) {
        carsRepair.setCarsRepairNumber(UUID.randomUUID().toString());
        carsRepair.setCreateTime(new Date());
        carsRepair.setUpdateTime(new Date());
        carsRepair.setStatus(0);
        carsRepair.setIsDelete(0);
        return carsRepairMapper.insert(carsRepair);
    }

    @Override
    public Integer delete(Integer carsRepairId) {
        return carsRepairMapper.deleteByPrimaryKey(carsRepairId);
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
    public CarsRepair selectCarsRepairById(Integer carsRepairId) {
        CarsRepair users = new CarsRepair();
        users.setCarsRepairId(carsRepairId);
        return carsRepairMapper.selectOne(users);
    }

    @Override
    public Set<String> selectLikeCarNumber(String carNumber) {
        Example example = new Example(CarsRepair.class);
        Example.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(carNumber)) {
            criteria.andLike("carsNumber", "%" + carNumber + "%");
        }
        List<CarsRepair> list = carsRepairMapper.selectByExample(example);
        list.sort(Comparator.comparing(CarsRepair::getUpdateTime).reversed());
        List<String> collect = list.stream().map(CarsRepair::getCarNumber).collect(Collectors.toList());
        return new HashSet<String>(collect);
    }

    @Override
    public Set<String> selectCarNumbers() {
        List<String> list = carsRepairMapper.selectCarNumbers();
        return new HashSet<String>(list);
    }

    @Override
    public PageData<CarsRepair> queryAllCarsRepairs(CarsRepairParameter carsRepairParameter) throws ParseException {
        PageHelper.startPage(carsRepairParameter.getPageNum(), carsRepairParameter.getPageSize());
        Example example = new Example(CarsRepair.class);
        Example.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(carsRepairParameter.getCarsRepairType())) {
            criteria.andLike("carsRepairType", "%" + carsRepairParameter.getCarsRepairType() + "%");
        }
        if (StringUtils.isNotBlank(carsRepairParameter.getUserName())) {
            criteria.andLike("userName", "%" + carsRepairParameter.getUserName() + "%");
        }
        if (StringUtils.isNotBlank(carsRepairParameter.getPhone())) {
            criteria.andLike("phone", "%" + carsRepairParameter.getPhone() + "%");
        }
        if (StringUtils.isNotBlank(carsRepairParameter.getCarNumber())) {
            criteria.andLike("carNumber", "%" + carsRepairParameter.getCarNumber() + "%");
        }
        if (StringUtils.isNotBlank(carsRepairParameter.getStartCreateTime()) && StringUtils.isBlank(carsRepairParameter.getEndCreateTime())) {
            criteria.andBetween("createTime", carsRepairParameter.getStartCreateTime(), DateUtils.dateIncrease(new Date(), 1));
        } else if (StringUtils.isNotBlank(carsRepairParameter.getStartCreateTime()) && StringUtils.isNotBlank(carsRepairParameter.getEndCreateTime())) {
            criteria.andBetween("createTime", carsRepairParameter.getStartCreateTime(), DateUtils.dateIncrease(DateUtils.strToDate(carsRepairParameter.getEndCreateTime()), 1));
        } else if (StringUtils.isBlank(carsRepairParameter.getStartCreateTime()) && StringUtils.isNotBlank(carsRepairParameter.getEndCreateTime())) {
            criteria.andBetween("createTime", "1970-01-01", DateUtils.dateIncrease(DateUtils.strToDate(carsRepairParameter.getEndCreateTime()), 1));
        }
        List<CarsRepair> list = carsRepairMapper.selectByExample(example);
        list.sort(Comparator.comparing(CarsRepair::getUpdateTime).reversed());
        PageInfo<CarsRepair> pageInfo = PageInfo.of(list);
        return new PageData<>(list, pageInfo.getTotal());
    }
}
