package cn.lvhaosir.service.impl;

import cn.lvhaosir.entity.Drivers;
import cn.lvhaosir.mapper.DriversMapper;
import cn.lvhaosir.paramater.DriverParameter;
import cn.lvhaosir.service.DriversService;
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
public class DriversServiceImpl implements DriversService {


	@Autowired
	private DriversMapper driversMapper;


	@Override
	public Integer add(Drivers driver) {
		driver.setCreateTime(new Date());
		driver.setUpdateTime(new Date());
		return driversMapper.insert(driver);
	}

	@Override
	public Integer delete(Object driverId) {
		return driversMapper.deleteByPrimaryKey(driverId);
	}

	@Override
	public Integer update(Drivers driver) {
		Example example = new Example(Drivers.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("driverId",driver.getDriverId());
		return driversMapper.updateByExampleSelective(driver, example);
	}

	@Override
	public Drivers selectDriverById(Integer driverId) {
		Drivers driver = new Drivers();
		driver.setDriverId(driverId);
		return driversMapper.selectOne(driver);
	}

	@Override
	public PageData<Drivers> queryLikeDrivers(Drivers driver) {
		PageHelper.startPage(driver.getPageNum(),driver.getPageSize());
		List<Drivers> list = driversMapper.queryLike(driver);
		PageInfo<Drivers> pageInfo = PageInfo.of(list);
		return new PageData<>(list, pageInfo.getTotal());
	}

	@Override
	public PageData<Drivers> allDrivers(DriverParameter driverParameter) {
		PageHelper.startPage(driverParameter.getPageNum(),driverParameter.getPageSize());
		Example example = new Example(Drivers.class);
		Example.Criteria criteria = example.createCriteria();

		if (StringUtils.isNotBlank(driverParameter.getDriverName())) {
			criteria.andLike("driverName", "%" + driverParameter.getDriverName() + "%");
		}
		if (StringUtils.isNotBlank(driverParameter.getSex())) {
			criteria.andLike("sex", "%" + driverParameter.getSex() + "%");
		}
		if (StringUtils.isNotBlank(driverParameter.getPhone())) {
			criteria.andLike("phone", "%" + driverParameter.getPhone() + "%");
		}
		if (StringUtils.isNotBlank(driverParameter.getAddress())) {
			criteria.andLike("address", "%" + driverParameter.getAddress()+ "%");
		}
		List<Drivers> list = driversMapper.selectByExample(example);
		list.sort(Comparator.comparing(Drivers::getUpdateTime).reversed());
		PageInfo<Drivers> pageInfo = PageInfo.of(list);
		return new PageData<>(list, pageInfo.getTotal());
	}
}
