package car.service.impl;


import car.entity.Insurance;
import car.mapper.InsuranceMapper;
import car.paramater.InsuranceParameter;
import car.service.InsuranceService;
import car.utils.BeanUtils;
import car.utils.DateUtils;
import car.utils.PageData;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.text.ParseException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class InsuranceServiceImpl implements InsuranceService {

    @Autowired
    private InsuranceMapper insuranceMapper;

    @Override
    public Integer add(InsuranceParameter insuranceParameter) {
        Insurance insurance = BeanUtils.copy(insuranceParameter, Insurance.class);
        insurance.setCreateTime(new Date());
        insurance.setUpdateTime(new Date());
        insurance.setStatus(0);
        insurance.setIsDelete(0);
        return insuranceMapper.insert(insurance);
    }

    @Override
    public Integer delete(String insuranceCode) {
        Insurance insurance = new Insurance();
        insurance.setIsDelete(1);
        insurance.setUpdateTime(new Date());
        Example example = new Example(Insurance.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("insuranceCode", insuranceCode);
        return insuranceMapper.updateByExampleSelective(insurance, example);
    }

    @Override
    public Integer update(InsuranceParameter insuranceParameter) {
        Insurance insurance = BeanUtils.copy(insuranceParameter, Insurance.class);
        insurance.setUpdateTime(new Date());
        Example example = new Example(Insurance.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("insuranceCode", insuranceParameter.getInsuranceCode());
        return insuranceMapper.updateByExampleSelective(insurance, example);
    }

    @Override
    public InsuranceParameter selectInsuranceByInsuranceCode(String InsuranceCode) {
        Insurance insurance = new Insurance();
        insurance.setInsuranceCode(InsuranceCode);
        Insurance insuring = insuranceMapper.selectOne(insurance);
        return BeanUtils.copy(insuring, InsuranceParameter.class);
    }


    @Override
    public PageData<Insurance> allInsurance(InsuranceParameter insuranceParameter) throws ParseException {
        PageHelper.startPage(insuranceParameter.getPageNum(), insuranceParameter.getPageSize());
        Example example = new Example(Insurance.class);
        Example.Criteria criteria = example.createCriteria();

        if (insuranceParameter.getIsDelete() != null) {
            criteria.andEqualTo("isDelete", insuranceParameter.getIsDelete());
        } else {
            criteria.andEqualTo("isDelete", 0);
        }
        if (StringUtils.isNotBlank(insuranceParameter.getStatus())) {
            criteria.andLike("status", "%" + insuranceParameter.getStatus() + "%");
        } else{
            criteria.andLike("status", "0");
        }
        if (StringUtils.isNotBlank(insuranceParameter.getInsuranceCompanyName())) {
            criteria.andLike("insuranceCompanyName", "%" + insuranceParameter.getInsuranceCompanyName() + "%");
        }
        if (StringUtils.isNotBlank(insuranceParameter.getInsuranceCode())) {
            criteria.andLike("insuranceCode", "%" + insuranceParameter.getInsuranceCode() + "%");
        }
        if (StringUtils.isNotBlank(insuranceParameter.getInsuranceUser())) {
            criteria.andLike("insuranceUser", "%" + insuranceParameter.getInsuranceUser() + "%");
        }
        if (StringUtils.isNotBlank(insuranceParameter.getInsuranceIdCard())) {
            criteria.andLike("insuranceIdCard", "%" + insuranceParameter.getInsuranceIdCard() + "%");
        }
        if (StringUtils.isNotBlank(insuranceParameter.getInsurancePhone())) {
            criteria.andLike("insurancePhone", "%" + insuranceParameter.getInsurancePhone() + "%");
        }
        if (StringUtils.isNotBlank(insuranceParameter.getStartCreateTime()) && StringUtils.isBlank(insuranceParameter.getEndCreateTime())) {
            criteria.andBetween("createTime", insuranceParameter.getStartCreateTime(), DateUtils.dateIncrease(new Date(), 1));
        } else if (StringUtils.isNotBlank(insuranceParameter.getStartCreateTime()) && StringUtils.isNotBlank(insuranceParameter.getEndCreateTime())) {
            criteria.andBetween("createTime", insuranceParameter.getStartCreateTime(), DateUtils.dateIncrease(DateUtils.strToDate(insuranceParameter.getEndCreateTime()), 1));
        } else if (StringUtils.isBlank(insuranceParameter.getStartCreateTime()) && StringUtils.isNotBlank(insuranceParameter.getEndCreateTime())) {
            criteria.andBetween("createTime", "1970-01-01", DateUtils.dateIncrease(DateUtils.strToDate(insuranceParameter.getEndCreateTime()), 1));
        }
        List<Insurance> list = insuranceMapper.selectByExample(example);
        list.sort(Comparator.comparing(Insurance::getUpdateTime).reversed());
        PageInfo<Insurance> pageInfo = PageInfo.of(list);
        return new PageData<>(list, pageInfo.getTotal());
    }

    @Override
    public List<String> selectInsuranceCompanyName() {
        List<String> strings = insuranceMapper.selectInsuranceCompanyName();
        Collections.reverse(strings);
        return strings;
    }

    @Override
    public List<String> selectInsuranceCode() {
        List<String> strings = insuranceMapper.selectInsuranceCode();
        Collections.reverse(strings);
        return strings;
    }

    @Override
    public List<String> selectInsuranceUser() {
        List<String> strings = insuranceMapper.selectInsuranceUser();
        Collections.reverse(strings);
        return strings;
    }

    @Override
    public List<String> selectInsuranceIdCard() {
        List<String> strings = insuranceMapper.selectInsuranceIdCard();
        Collections.reverse(strings);
        return strings;
    }

    @Override
    public List<String> selectInsurancePhone() {
        List<String> strings = insuranceMapper.selectInsurancePhone();
        Collections.reverse(strings);
        return strings;
    }
}
