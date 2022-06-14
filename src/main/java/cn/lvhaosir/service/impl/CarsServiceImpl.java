package cn.lvhaosir.service.impl;

import cn.lvhaosir.entity.Cars;
import cn.lvhaosir.utils.PageData;
import cn.lvhaosir.utils.PageParam;
import cn.lvhaosir.mapper.CarsMapper;
import cn.lvhaosir.service.CarsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;


@Service
public class CarsServiceImpl implements CarsService {

    @Autowired
    private CarsMapper carsMapper;

    @Override
    public Integer add(Cars car) {
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
        criteria.andEqualTo("engineNumber", car.getEngineNumber());
        return carsMapper.updateByExampleSelective(car, example);
    }

    @Override
    public Cars selectCarById(Integer id) {
        Cars cars = new Cars();
        cars.setCarId(id);
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
    public PageData<Cars> allCars(PageParam pageParam) {
        PageHelper.startPage(pageParam.getPageNum(),pageParam.getPageSize());
        List<Cars> list = carsMapper.selectAll();
        PageInfo<Cars> pageInfo = PageInfo.of(list);
        return new PageData<>(list, pageInfo.getTotal());
    }
}
