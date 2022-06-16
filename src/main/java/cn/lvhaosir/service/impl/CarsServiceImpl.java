package cn.lvhaosir.service.impl;

import cn.lvhaosir.entity.Cars;
import cn.lvhaosir.mapper.CarsMapper;
import cn.lvhaosir.paramater.CarParameter;
import cn.lvhaosir.service.CarsService;
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
public class CarsServiceImpl implements CarsService {

    @Autowired
    private CarsMapper carsMapper;

    @Override
    public Integer add(Cars car) {
        car.setCreateTime(new Date());
        car.setUpdateTime(new Date());
        return carsMapper.insert(car);
    }

    @Override
    public Integer delete(Integer id) {
        return carsMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Integer update(Cars car) {
        Example example = new Example(Cars.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("carId", car.getCarId());
        return carsMapper.updateByExampleSelective(car, example);
    }

    @Override
    public Cars selectCarById(Integer carId) {
        Cars cars = new Cars();
        cars.setCarId(carId);
        return carsMapper.selectOne(cars);
    }

    @Override
    public PageData<Cars> queryLikeCars(Cars car) {
        PageHelper.startPage(car.getPageNum(), car.getPageSize());
        List<Cars> list = carsMapper.select(car);
        PageInfo<Cars> pageInfo = PageInfo.of(list);
        return new PageData<>(list, pageInfo.getTotal());
    }

    @Override
    public PageData<Cars> allCars(CarParameter carParameter) {
        PageHelper.startPage(carParameter.getPageNum(),carParameter.getPageSize());
        Example example = new Example(Cars.class);
        Example.Criteria criteria = example.createCriteria();

        if (StringUtils.isNotBlank(carParameter.getCarBrand())) {
            criteria.andLike("carBrand", "%" + carParameter.getCarBrand() + "%");
        }
        if (StringUtils.isNotBlank(carParameter.getCarName())) {
            criteria.andLike("carName", "%" + carParameter.getCarName() + "%");
        }
        if (StringUtils.isNotBlank(carParameter.getCarNumber())) {
            criteria.andLike("carNumber", "%" + carParameter.getCarNumber() + "%");
        }
        if (StringUtils.isNotBlank(carParameter.getEngineNumber())) {
            criteria.andLike("engineNumber", "%" + carParameter.getEngineNumber() + "%");
        }
        List<Cars> list = carsMapper.selectByExample(example);
        list.sort(Comparator.comparing(Cars::getUpdateTime).reversed());
        PageInfo<Cars> pageInfo = PageInfo.of(list);
        return new PageData<>(list, pageInfo.getTotal());
    }
}
