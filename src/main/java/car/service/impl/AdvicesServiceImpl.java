package car.service.impl;


import car.entity.Advices;
import car.entity.CarsRepair;
import car.mapper.AdvicesMapper;
import car.mapper.CarsRepairMapper;
import car.paramater.AdvicesParameter;
import car.service.AdvicesService;
import car.utils.BeanUtils;
import car.utils.PageData;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Comparator;
import java.util.List;


@Service
public class AdvicesServiceImpl implements AdvicesService {

    @Autowired
    private CarsRepairMapper carsRepairMapper;

    @Autowired
    private AdvicesMapper advicesMapper;


    @Override
    public Advices selectAdvicesByCarsRepairNumber(String carsRepairNumber) {
        CarsRepair carsRepair = new CarsRepair();
        carsRepair.setCarsRepairNumber(carsRepairNumber);
        CarsRepair carsRepair1 = carsRepairMapper.selectOne(carsRepair);
        return BeanUtils.copy(carsRepair1, Advices.class);
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

        List<Advices> list = advicesMapper.selectByExample(example);
        list.sort(Comparator.comparing(Advices::getUpdateTime).reversed());
        PageInfo<Advices> pageInfo = PageInfo.of(list);
        return new PageData<>(list, pageInfo.getTotal());
    }
}
