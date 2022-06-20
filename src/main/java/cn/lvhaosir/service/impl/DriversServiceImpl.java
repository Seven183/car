package cn.lvhaosir.service.impl;

import cn.lvhaosir.entity.CarsRepair;
import cn.lvhaosir.entity.Drivers;
import cn.lvhaosir.mapper.CarsRepairMapper;
import cn.lvhaosir.mapper.DriversMapper;
import cn.lvhaosir.paramater.DriverParameter;
import cn.lvhaosir.service.DriversService;
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
public class DriversServiceImpl implements DriversService {


	@Autowired
	private DriversMapper driversMapper;

	@Autowired
	private CarsRepairMapper carsRepairMapper;

	@Override
	public Integer add(Drivers driver) {
		driver.setCreateTime(new Date());
		driver.setUpdateTime(new Date());
		driver.setIsDelete(0);
		return driversMapper.insert(driver);
	}

	@Override
	public Integer delete(Integer driverId) {
		Drivers drivers = new Drivers();
		drivers.setIsDelete(1);
		drivers.setUpdateTime(new Date());
		Example example = new Example(Drivers.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("driverId", driverId);
		return driversMapper.updateByExampleSelective(drivers, example);
//		return driversMapper.deleteByPrimaryKey(driverId);
	}

	@Override
	public Integer update(Drivers driver) {
		CarsRepair copy = BeanUtils.copy(driver, CarsRepair.class);
		Example example = new Example(CarsRepair.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("carsRepairNumber",copy.getCarsRepairNumber());
		return carsRepairMapper.updateByExampleSelective(copy, example);
	}

	@Override
	public Drivers selectDriverById(Integer driverId) {
		Drivers driver = new Drivers();
		driver.setDriverId(driverId);
		return driversMapper.selectOne(driver);
	}

	@Override
	public Drivers selectDriverByCarsRepairNumber(String carsRepairNumber) {
		CarsRepair carsRepair = new CarsRepair();
		carsRepair.setCarsRepairNumber(carsRepairNumber);
		CarsRepair carsRepair1 = carsRepairMapper.selectOne(carsRepair);
		return BeanUtils.copy(carsRepair1,Drivers.class);
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

		if (driverParameter.getIsDelete() != null) {
			criteria.andEqualTo("isDelete", driverParameter.getIsDelete());
		} else {
			criteria.andEqualTo("isDelete", 0);
		}
		if (StringUtils.isNotBlank(driverParameter.getStatus())) {
			criteria.andLike("status", "%" + driverParameter.getStatus() + "%");
		} else{
			criteria.andLike("status", "0");
		}
		if (StringUtils.isNotBlank(driverParameter.getCarNumber())) {
			criteria.andLike("carNumber", "%" + driverParameter.getCarNumber() + "%");
		}
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
		if (StringUtils.isNotBlank(driverParameter.getCarBrand())) {
			criteria.andLike("carBrand", "%" + driverParameter.getCarBrand()+ "%");
		}

		List<CarsRepair> listCarsRepair = carsRepairMapper.selectByExample(example);
		List<Drivers> list = BeanUtils.copyList(listCarsRepair,Drivers.class);
		list.sort(Comparator.comparing(Drivers::getUpdateTime).reversed());
		PageInfo<Drivers> pageInfo = PageInfo.of(list);
		return new PageData<>(list, pageInfo.getTotal());
	}
}
