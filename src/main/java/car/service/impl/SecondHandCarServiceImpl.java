package car.service.impl;


import car.entity.CarsRepair;
import car.entity.Insurance;
import car.entity.SecondHandCar;
import car.mapper.SecondHandCarMapper;
import car.paramater.SecondHandCarParameter;
import car.service.SecondHandCarService;
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
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class SecondHandCarServiceImpl implements SecondHandCarService {

    @Autowired
    private SecondHandCarMapper secondHandCarMapper;

    @Override
    public Integer add(SecondHandCarParameter secondHandCarParameter) {
        SecondHandCar secondHandCar = BeanUtils.copy(secondHandCarParameter, SecondHandCar.class);
        JSONArray arrayCarPhoto= JSONArray.parseArray(JSON.toJSONString(secondHandCarParameter.getCarPhoto()));
        secondHandCar.setSecondHandCarPhotoJson(arrayCarPhoto.toJSONString());
        secondHandCar.setCreateTime(new Date());
        secondHandCar.setUpdateTime(new Date());
        secondHandCar.setStatus(0);
        secondHandCar.setIsDelete(0);
        return secondHandCarMapper.insert(secondHandCar);
    }

    @Override
    public Integer delete(String secondHandCarId) {
        SecondHandCar secondHandCar = new SecondHandCar();
        secondHandCar.setIsDelete(1);
        secondHandCar.setUpdateTime(new Date());
        Example example = new Example(Insurance.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("secondHandCarId", secondHandCarId);
        return secondHandCarMapper.updateByExampleSelective(secondHandCar, example);
    }

    @Override
    public Integer update(SecondHandCarParameter secondHandCarParameter) {
        SecondHandCar secondHandCar = BeanUtils.copy(secondHandCarParameter, SecondHandCar.class);
        JSONArray arrayCarPhoto= JSONArray.parseArray(JSON.toJSONString(secondHandCarParameter.getCarPhoto()));
        secondHandCar.setSecondHandCarPhotoJson(arrayCarPhoto.toJSONString());
        secondHandCar.setUpdateTime(new Date());
        Example example = new Example(SecondHandCar.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("secondHandCarId", secondHandCarParameter.getSecondHandCarId());
        return secondHandCarMapper.updateByExampleSelective(secondHandCar, example);
    }

    @Override
    public SecondHandCarParameter selectSecondHandCarById(Integer secondHandCarId) {
        SecondHandCar secondHandCar = new SecondHandCar();
        secondHandCar.setSecondHandCarId(secondHandCarId);
        SecondHandCar secondHandCa = secondHandCarMapper.selectOne(secondHandCar);
        SecondHandCarParameter secondHandCarParameter = BeanUtils.copy(secondHandCa, SecondHandCarParameter.class);
        String carPhoto = secondHandCa.getSecondHandCarPhotoJson();
        List<CarsRepair.CarPhoto> listCarPhoto = JSONObject.parseArray(carPhoto, CarsRepair.CarPhoto.class);
        secondHandCarParameter.setCarPhoto(listCarPhoto);
        return secondHandCarParameter;
    }


    @Override
    public PageData<SecondHandCar> allSecondHandCar(SecondHandCarParameter secondHandCarParameter) throws ParseException {
        PageHelper.startPage(secondHandCarParameter.getPageNum(), secondHandCarParameter.getPageSize());
        Example example = new Example(SecondHandCar.class);
        Example.Criteria criteria = example.createCriteria();

        if (secondHandCarParameter.getIsDelete() != null) {
            criteria.andEqualTo("isDelete", secondHandCarParameter.getIsDelete());
        } else {
            criteria.andEqualTo("isDelete", 0);
        }
        if (StringUtils.isNotBlank(secondHandCarParameter.getStatus())) {
            criteria.andLike("status", "%" + secondHandCarParameter.getStatus() + "%");
        } else{
            criteria.andLike("status", "0");
        }
        if (StringUtils.isNotBlank(secondHandCarParameter.getBuyerUser())) {
            criteria.andLike("buyerUser", "%" + secondHandCarParameter.getBuyerUser() + "%");
        }
        if (StringUtils.isNotBlank(secondHandCarParameter.getBuyerIdCard())) {
            criteria.andLike("buyerIdCard", "%" + secondHandCarParameter.getBuyerIdCard() + "%");
        }
        if (StringUtils.isNotBlank(secondHandCarParameter.getBuyerPhone())) {
            criteria.andLike("buyerPhone", "%" + secondHandCarParameter.getBuyerPhone() + "%");
        }
        if (StringUtils.isNotBlank(secondHandCarParameter.getSellerUser())) {
            criteria.andLike("sellerUser", "%" + secondHandCarParameter.getSellerUser() + "%");
        }
        if (StringUtils.isNotBlank(secondHandCarParameter.getSellerIdCard())) {
            criteria.andLike("sellerIdCard", "%" + secondHandCarParameter.getSellerIdCard() + "%");
        }
        if (StringUtils.isNotBlank(secondHandCarParameter.getSellerPhone())) {
            criteria.andLike("sellerPhone", "%" + secondHandCarParameter.getSellerPhone() + "%");
        }
        if (StringUtils.isNotBlank(secondHandCarParameter.getSecondHandCarEngineNumber())) {
            criteria.andLike("secondHandCarEngineNumber", "%" + secondHandCarParameter.getSecondHandCarEngineNumber() + "%");
        }
        if (StringUtils.isNotBlank(secondHandCarParameter.getSecondHandCarName())) {
            criteria.andLike("secondHandCarName", "%" + secondHandCarParameter.getSecondHandCarName() + "%");
        }
        if (StringUtils.isNotBlank(secondHandCarParameter.getSecondHandCarNumber())) {
            criteria.andLike("secondHandCarNumber", "%" + secondHandCarParameter.getSecondHandCarNumber() + "%");
        }
        if (StringUtils.isNotBlank(secondHandCarParameter.getSecondHandCarBrand())) {
            criteria.andLike("secondHandCarBrand", "%" + secondHandCarParameter.getSecondHandCarBrand() + "%");
        }
        if (StringUtils.isNotBlank(secondHandCarParameter.getStartCreateTime()) && StringUtils.isBlank(secondHandCarParameter.getEndCreateTime())) {
            criteria.andBetween("createTime", secondHandCarParameter.getStartCreateTime(), DateUtils.dateIncrease(new Date(), 1));
        } else if (StringUtils.isNotBlank(secondHandCarParameter.getStartCreateTime()) && StringUtils.isNotBlank(secondHandCarParameter.getEndCreateTime())) {
            criteria.andBetween("createTime", secondHandCarParameter.getStartCreateTime(), DateUtils.dateIncrease(DateUtils.strToDate(secondHandCarParameter.getEndCreateTime()), 1));
        } else if (StringUtils.isBlank(secondHandCarParameter.getStartCreateTime()) && StringUtils.isNotBlank(secondHandCarParameter.getEndCreateTime())) {
            criteria.andBetween("createTime", "1970-01-01", DateUtils.dateIncrease(DateUtils.strToDate(secondHandCarParameter.getEndCreateTime()), 1));
        }
        List<SecondHandCar> list = secondHandCarMapper.selectByExample(example);
        list.sort(Comparator.comparing(SecondHandCar::getUpdateTime).reversed());
        PageInfo<SecondHandCar> pageInfo = PageInfo.of(list);
        return new PageData<>(list, pageInfo.getTotal());
    }

    @Override
    public List<String> selectCarNumbers() {
        List<String> strings = secondHandCarMapper.selectCarNumbers();
        Collections.reverse(strings);
        return strings;

    }

    @Override
    public List<String> selectCarBrands() {
        List<String> strings = secondHandCarMapper.selectCarBrands();
        Collections.reverse(strings);
        return strings;
    }

    @Override
    public List<String> selectBuyerPhones() {
        List<String> strings = secondHandCarMapper.selectBuyerPhones();
        Collections.reverse(strings);
        return strings;
    }

    @Override
    public List<String> selectBuyerUsers() {
        List<String> strings = secondHandCarMapper.selectBuyerUsers();
        Collections.reverse(strings);
        return strings;
    }

    @Override
    public List<String> selectBuyerIdCards() {
        List<String> strings = secondHandCarMapper.selectBuyerIdCards();
        Collections.reverse(strings);
        return strings;
    }
}
