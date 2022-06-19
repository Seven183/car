package cn.lvhaosir.service.impl;


import cn.lvhaosir.entity.Advices;
import cn.lvhaosir.entity.CarsRepair;
import cn.lvhaosir.entity.Drivers;
import cn.lvhaosir.mapper.AdvicesMapper;
import cn.lvhaosir.mapper.CarsRepairMapper;
import cn.lvhaosir.mapper.DriversMapper;
import cn.lvhaosir.paramater.CarsRepairParameter;
import cn.lvhaosir.result.CarsRepairDetails;
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

@Service
public class CarsRepairServiceImpl implements CarsRepairService {


    @Autowired
    private CarsRepairMapper carsRepairMapper;

    @Autowired
    private AdvicesMapper advicesMapper;

    @Autowired
    private DriversMapper driversMapper;


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
        CarsRepair carsRepair = new CarsRepair();
        carsRepair.setIsDelete(1);
        carsRepair.setUpdateTime(new Date());
        Example example = new Example(CarsRepair.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("carsRepairId", carsRepairId);
        return carsRepairMapper.updateByExampleSelective(carsRepair, example);
//        return carsRepairMapper.deleteByPrimaryKey(carsRepairId);
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
    public CarsRepairDetails detailsByCarNumber(String carNumber) {

        Example carsRepair = new Example(CarsRepair.class);
        Example.Criteria criteriaCarsRepair = carsRepair.createCriteria();
        criteriaCarsRepair.andEqualTo("carNumber", carNumber);
        List<CarsRepair> listCarsRepair = carsRepairMapper.selectByExample(carsRepair);

        Example advices = new Example(Advices.class);
        Example.Criteria criteriaAdvices = advices.createCriteria();
        criteriaAdvices.andEqualTo("carNumber", carNumber);
        List<Advices> listAdvices = advicesMapper.selectByExample(advices);

        Example exampleDrivers = new Example(Drivers.class);
        Example.Criteria criteriaDrivers = exampleDrivers.createCriteria();
        criteriaDrivers.andEqualTo("carNumber", carNumber);
        List<Drivers> listDrivers = driversMapper.selectByExample(exampleDrivers);

        CarsRepairDetails carsRepairDetails = new CarsRepairDetails();
        String carsRepairNumber = "";
        String carsRepairType = "";
        String carsRepairText = "";
        String createTime = "";
        for (CarsRepair list : listCarsRepair) {
            carsRepairNumber = carsRepairNumber + list.getCarsRepairNumber() + ",";
            carsRepairType = carsRepairType + list.getCarsRepairType() + ",";
            carsRepairText = carsRepairText + list.getCarsRepairText() + ",";
            createTime = createTime + list.getCreateTime() + ",";
        }

        String advicesType = "";
        String advicesName = "";
        String advicesNumber = "";
        String advicesQuantity = "";
        String advicesPriceAmount = "";
        String advicesFullAmount = "";
        for (Advices list : listAdvices) {
            advicesType = advicesType + list.getAdvicesType() + ",";
            advicesName = advicesName + list.getAdvicesNumber() + ",";
            advicesNumber = advicesNumber + list.getAdvicesName() + ",";
            advicesQuantity = advicesQuantity + list.getAdvicesQuantity() + ",";
            advicesPriceAmount = advicesPriceAmount + list.getAdvicesPriceAmount() + ",";
            advicesFullAmount = advicesFullAmount + list.getAdvicesFullAmount() + ",";
        }


        String userName = "";
        String age = "";
        String sex = "";
        String phone = "";
        String address = "";
        String carBrand = "";
        String carName = "";
        String engineNumber = "";
        for (Drivers list : listDrivers ) {
            userName = userName + list.getDriverName() + ",";
            age = age + list.getAge() + ",";
            sex = sex + list.getSex() + ",";
            phone = phone + list.getPhone() + ",";
            address = address + list.getAddress() + ",";
            carBrand = carBrand + list.getCarBrand() + ",";
            carName = carName + list.getCarName() + ",";
            engineNumber = engineNumber + list.getEngineNumber() + ",";

        }

        return carsRepairDetails;
    }

    @Override
    public Set<String> selectCarNumbers() {
        List<String> list = carsRepairMapper.selectCarNumbers();
        return new HashSet<String>(list);
    }

    @Override
    public Integer statusOperate(Integer carsRepairId, Integer status) {
        CarsRepair carsRepair = new CarsRepair();
        carsRepair.setStatus(status);
        carsRepair.setUpdateTime(new Date());
        Example example = new Example(CarsRepair.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("carsRepairId", carsRepairId);
        return carsRepairMapper.updateByExampleSelective(carsRepair, example);
    }

    @Override
    public PageData<CarsRepair> queryAllCarsRepairs(CarsRepairParameter carsRepairParameter) throws ParseException {
        PageHelper.startPage(carsRepairParameter.getPageNum(), carsRepairParameter.getPageSize());
        Example example = new Example(CarsRepair.class);
        Example.Criteria criteria = example.createCriteria();

        if (carsRepairParameter.getIsDelete() != null) {
            criteria.andEqualTo("isDelete", carsRepairParameter.getIsDelete());
        } else {
            criteria.andEqualTo("isDelete", 0);
        }
        if (StringUtils.isNotBlank(carsRepairParameter.getStatus())) {
            criteria.andLike("status", "%" + carsRepairParameter.getStatus() + "%");
        } else{
            criteria.andLike("status", "0");
        }
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
