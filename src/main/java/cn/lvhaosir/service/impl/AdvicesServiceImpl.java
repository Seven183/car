package cn.lvhaosir.service.impl;


import cn.lvhaosir.entity.Advices;
import cn.lvhaosir.entity.CarsRepair;
import cn.lvhaosir.mapper.AdvicesMapper;
import cn.lvhaosir.mapper.CarsRepairMapper;
import cn.lvhaosir.paramater.AdvicesParameter;
import cn.lvhaosir.service.AdvicesService;
import cn.lvhaosir.utils.BeanUtils;
import cn.lvhaosir.utils.PageData;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Comparator;
import java.util.Date;
import java.util.List;


@Service
public class AdvicesServiceImpl implements AdvicesService {

    @Autowired
    private CarsRepairMapper carsRepairMapper;

    @Autowired
    private AdvicesMapper advicesMapper;

    @Override
    public Integer add(Advices advice) {
        advice.setCreateTime(new Date());
        advice.setUpdateTime(new Date());
        advice.setIsDelete(0);
        return advicesMapper.insert(advice);
    }

    @Override
    public Integer delete(Integer id) {
        Advices advices = new Advices();
        advices.setIsDelete(1);
        advices.setUpdateTime(new Date());
        Example example = new Example(Advices.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("advicesId", id);
        return advicesMapper.updateByExampleSelective(advices, example);
//        return advicesMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Integer update(Advices advice) {
        CarsRepair copy = BeanUtils.copy(advice, CarsRepair.class);
        Example example = new Example(CarsRepair.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("carsRepairNumber",copy.getCarsRepairNumber());
        return carsRepairMapper.updateByExampleSelective(copy, example);
    }

    @Override
    public Advices selectAdvicesById(Integer id) {
        Advices advices = new Advices();
        advices.setAdvicesId(id);
        return advicesMapper.selectOne(advices);
    }

    @Override
    public Advices selectAdvicesByCarsRepairNumber(String carsRepairNumber) {
        CarsRepair carsRepair = new CarsRepair();
        carsRepair.setCarsRepairNumber(carsRepairNumber);
        CarsRepair carsRepair1 = carsRepairMapper.selectOne(carsRepair);
        return BeanUtils.copy(carsRepair1, Advices.class);
    }

    @Override
    public PageData<Advices> queryLikeAdvices(Advices advice) {
        PageHelper.startPage(advice.getPageNum(), advice.getPageSize());
        List<Advices> list = advicesMapper.select(advice);
        PageInfo<Advices> pageInfo = PageInfo.of(list);
        return new PageData<>(list, pageInfo.getTotal());
    }

    @Override
    public PageData<Advices> allAdvices(AdvicesParameter advicesParameter) {

        PageHelper.startPage(advicesParameter.getPageNum(), advicesParameter.getPageSize());
        Example example = new Example(Advices.class);
        Example.Criteria criteria = example.createCriteria();

        if (advicesParameter.getIsDelete() != null) {
            criteria.andEqualTo("isDelete", advicesParameter.getIsDelete());
        } else {
            criteria.andEqualTo("isDelete", 0);
        }
        if (StringUtils.isNotBlank(advicesParameter.getStatus())) {
            criteria.andLike("status", "%" + advicesParameter.getStatus() + "%");
        } else{
            criteria.andLike("status", "0");
        }
        if (StringUtils.isNotBlank(advicesParameter.getCarNumber())) {
            criteria.andLike("carNumber", "%" + advicesParameter.getCarNumber() + "%");
        }
        if (StringUtils.isNotBlank(advicesParameter.getAdvicesType())) {
            criteria.andLike("advicesType", "%" + advicesParameter.getAdvicesType() + "%");
        }
        if (StringUtils.isNotBlank(advicesParameter.getAdvicesName())) {
            criteria.andLike("advicesName", "%" + advicesParameter.getAdvicesName() + "%");
        }
        if (StringUtils.isNotBlank(advicesParameter.getAdvicesNumber())) {
            criteria.andLike("advicesNumber", "%" + advicesParameter.getAdvicesNumber() + "%");
        }

        List<CarsRepair> listCarsRepair = carsRepairMapper.selectByExample(example);
        List<Advices> list = BeanUtils.copyList(listCarsRepair,Advices.class);
        list.sort(Comparator.comparing(Advices::getUpdateTime).reversed());
        PageInfo<Advices> pageInfo = PageInfo.of(list);
        return new PageData<>(list, pageInfo.getTotal());
    }
}
