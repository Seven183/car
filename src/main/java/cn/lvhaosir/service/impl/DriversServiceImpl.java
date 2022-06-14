package cn.lvhaosir.service.impl;

import cn.lvhaosir.entity.Drivers;
import cn.lvhaosir.utils.PageData;
import cn.lvhaosir.utils.PageParam;
import cn.lvhaosir.mapper.DriversMapper;
import cn.lvhaosir.service.DriversService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class DriversServiceImpl implements DriversService {


	@Autowired
	private DriversMapper driversMapper;


	@Override
	public Integer add(Drivers driver) {
		return driversMapper.insert(driver);
	}

	@Override
	public Integer delete(Object id) {
		return driversMapper.deleteByPrimaryKey(id);
	}

	@Override
	public Integer update(Drivers driver) {
		Example example = new Example(Drivers.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("driverName",driver.getDriverName());
		return driversMapper.updateByExampleSelective(driver, example);
	}

	@Override
	public Drivers selectDriverById(Integer id) {
		Drivers driver = new Drivers();
		driver.setDriverId(id);
		return driversMapper.selectOne(driver);
	}

	@Override
	public PageData<Drivers> queryLikeDrivers(Drivers driver) {
		PageHelper.startPage(driver.getPageNum(),driver.getPageSize());
//		Example example = new Example(Drivers.class);
//		Example.Criteria criteria = example.createCriteria();
//		criteria.andEqualTo("driverName",driver.getDriverName());
		List<Drivers> list = driversMapper.queryLike(driver);
		PageInfo<Drivers> pageInfo = PageInfo.of(list);
		return new PageData<>(list, pageInfo.getTotal());
	}

	@Override
	public PageData<Drivers> allDrivers(PageParam pageParam) {
		PageHelper.startPage(pageParam.getPageNum(),pageParam.getPageSize());
		List<Drivers> list = driversMapper.selectAll();
		PageInfo<Drivers> pageInfo = PageInfo.of(list);
		return new PageData<>(list, pageInfo.getTotal());
	}
}
