package car.service.impl;


import car.entity.Advices;
import car.entity.CarsRepair;
import car.mapper.AdvicesMapper;
import car.mapper.CarsRepairMapper;
import car.paramater.CarsRepairParameter;
import car.service.CarsRepairService;
import car.utils.BeanUtils;
import car.utils.DateUtils;
import car.utils.PageData;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.text.ParseException;
import java.util.*;

@Service
@Transactional
public class CarsRepairServiceImpl implements CarsRepairService {


    @Autowired
    private CarsRepairMapper carsRepairMapper;

    @Autowired
    private AdvicesMapper advicesMapper;


    @Override
    public Integer add(CarsRepairParameter carsRepairParameter) {

        // 插入大宽表
        String carsRepairNumber = UUID.randomUUID().toString();
        CarsRepair carsRepair = BeanUtils.copy(carsRepairParameter, CarsRepair.class);
        JSONArray arrayCarPhoto= JSONArray.parseArray(JSON.toJSONString(carsRepairParameter.getCarPhoto()));
        carsRepair.setCarPhotoJson(arrayCarPhoto.toJSONString());
        JSONArray array= JSONArray.parseArray(JSON.toJSONString(carsRepairParameter.getAdvicesItems()));
        carsRepair.setAdvicesJson(array.toJSONString());
        carsRepair.setCarsRepairNumber(carsRepairNumber);
        carsRepair.setCreateTime(new Date());
        carsRepair.setUpdateTime(new Date());
        carsRepair.setStatus(0);
        carsRepair.setIsDelete(0);
        carsRepairMapper.insert(carsRepair);

        //插入小表
        List<CarsRepair.Advices> advicesItems = carsRepairParameter.getAdvicesItems();
        for (CarsRepair.Advices advicesItem: advicesItems) {
            Advices advices = BeanUtils.copy(advicesItem, Advices.class);
            advices.setCarsRepairNumber(carsRepairNumber);
            advices.setCarNumber(carsRepairParameter.getCarNumber());
            advices.setCreateTime(new Date());
            advices.setUpdateTime(new Date());
            advices.setStatus(0);
            advices.setIsDelete(0);
            advicesMapper.insert(advices);
        }
        return 1;
    }

    @Override
    public Integer delete(String carsRepairNumber) {
        CarsRepair carsRepair = new CarsRepair();
        carsRepair.setIsDelete(1);
        carsRepair.setUpdateTime(new Date());
        Example example = new Example(CarsRepair.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("carsRepairNumber", carsRepairNumber);
        carsRepairMapper.updateByExampleSelective(carsRepair, example);

        Advices advices = new Advices();
        advices.setIsDelete(1);
        advices.setUpdateTime(new Date());
        Example exampleAdvices = new Example(Advices.class);
        Example.Criteria criteriaAdvices = exampleAdvices.createCriteria();
        criteriaAdvices.andEqualTo("carsRepairNumber", carsRepairNumber);
        advicesMapper.updateByExampleSelective(advices, example);
        return 1;
    }

    @Override
    public Integer update(CarsRepairParameter carsRepairParameter) {

        CarsRepair carsRepair = BeanUtils.copy(carsRepairParameter, CarsRepair.class);
        JSONArray array= JSONArray.parseArray(JSON.toJSONString(carsRepairParameter.getAdvicesItems()));
        carsRepair.setAdvicesJson(array.toJSONString());
        JSONArray arrayCarPhoto= JSONArray.parseArray(JSON.toJSONString(carsRepairParameter.getCarPhoto()));
        carsRepair.setCarPhotoJson(arrayCarPhoto.toJSONString());
        carsRepair.setUpdateTime(new Date());
        Example example = new Example(CarsRepair.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("carsRepairNumber", carsRepair.getCarsRepairNumber());
        carsRepairMapper.updateByExampleSelective(carsRepair, example);

        Advices advices = new Advices();
        advices.setCarsRepairNumber(carsRepair.getCarsRepairNumber());
        int delete = advicesMapper.delete(advices);

        if (delete ==1 ){
            List<CarsRepair.Advices> advicesItems = carsRepairParameter.getAdvicesItems();
            for (CarsRepair.Advices advicesItem: advicesItems) {
                Advices advicess = BeanUtils.copy(advicesItem, Advices.class);
                advicess.setCarsRepairNumber(carsRepairParameter.getCarsRepairNumber());
                advicess.setCarNumber(carsRepairParameter.getCarNumber());
                advicess.setCreateTime(new Date());
                advicess.setUpdateTime(new Date());
                advicess.setStatus(0);
                advicess.setIsDelete(0);
                advicesMapper.insert(advicess);
            }
        }
        return 1;
    }

    @Override
    public CarsRepairParameter selectCarsRepairByCarsRepairNumber(String carsRepairNumber) {
        CarsRepair users = new CarsRepair();
        users.setCarsRepairNumber(carsRepairNumber);
        CarsRepair carsRepair = carsRepairMapper.selectOne(users);
        CarsRepairParameter carsRepairParameter = BeanUtils.copy(carsRepair, CarsRepairParameter.class);
        String advicesItems = carsRepair.getAdvicesJson();
        String carPhoto = carsRepair.getCarPhotoJson();
        List<CarsRepair.Advices> list = JSONObject.parseArray(advicesItems, CarsRepair.Advices.class);
        carsRepairParameter.setAdvicesItems(list);
        List<CarsRepair.CarPhoto> listCarPhoto = JSONObject.parseArray(carPhoto, CarsRepair.CarPhoto.class);
        carsRepairParameter.setCarPhoto(listCarPhoto);
        return carsRepairParameter;
    }

    @Override
    public CarsRepairParameter detailsByCarsRepairNumber(String carsRepairNumber) {

        Example carsRepair = new Example(CarsRepair.class);
        Example.Criteria criteriaCarsRepair = carsRepair.createCriteria();
        criteriaCarsRepair.andEqualTo("carsRepairNumber", carsRepairNumber);
        List<CarsRepair> listCarsRepair = carsRepairMapper.selectByExample(carsRepair);
        CarsRepairParameter carsRepairParameter = BeanUtils.copy(listCarsRepair.get(0), CarsRepairParameter.class);
        List<CarsRepair.Advices> list = JSONObject.parseArray(listCarsRepair.get(0).getAdvicesJson(), CarsRepair.Advices.class);
        List<CarsRepair.CarPhoto> listCarPhoto = JSONObject.parseArray(listCarsRepair.get(0).getCarPhotoJson(), CarsRepair.CarPhoto.class);
        carsRepairParameter.setAdvicesItems(list);
        carsRepairParameter.setCarPhoto(listCarPhoto);
        return carsRepairParameter;
    }

    @Override
    public Integer statusOperate(String carsRepairNumber, Integer status) {
        CarsRepair carsRepair = new CarsRepair();
        carsRepair.setStatus(status);
        carsRepair.setUpdateTime(new Date());
        carsRepair.setEndTime(new Date());
        Example example = new Example(CarsRepair.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("carsRepairNumber", carsRepairNumber);
        carsRepairMapper.updateByExampleSelective(carsRepair, example);

        Advices advices = new Advices();
        advices.setStatus(status);
        advices.setUpdateTime(new Date());
        advices.setEndTime(new Date());
        Example exampleAdvices = new Example(Advices.class);
        Example.Criteria criteriaAdvices = exampleAdvices.createCriteria();
        criteriaAdvices.andEqualTo("carsRepairNumber", carsRepairNumber);
        advicesMapper.updateByExampleSelective(advices, example);
        return 1;
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

    @Override
    public List<String> selectCarNumbers() {
        List<String> strings = carsRepairMapper.selectCarNumbers();
        Collections.reverse(strings);
        return strings;
    }

    @Override
    public List<String> selectCarsRepairType() {
        List<String> strings = carsRepairMapper.selectCarsRepairType();
        Collections.reverse(strings);
        return strings;
    }

    @Override
    public List<String> selectPhone() {
        List<String> strings = carsRepairMapper.selectPhone();
        Collections.reverse(strings);
        return strings;
    }
}
