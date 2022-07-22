package car.service.impl;

import car.entity.CarsRepair;
import car.entity.Drivers;
import car.mapper.CarsRepairMapper;
import car.mapper.DriversMapper;
import car.paramater.DriverParameter;
import car.service.DriversService;
import car.utils.BeanUtils;
import car.utils.PageData;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class DriversServiceImpl implements DriversService {


	@Autowired
	private DriversMapper driversMapper;

	@Autowired
	private CarsRepairMapper carsRepairMapper;


	@Override
	public Integer update(Drivers driver) {
		CarsRepair copy = BeanUtils.copy(driver, CarsRepair.class);
		Example example = new Example(CarsRepair.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("carsRepairNumber",copy.getCarsRepairNumber());
		return carsRepairMapper.updateByExampleSelective(copy, example);
	}

	@Override
	public DriverParameter selectDriverByCarsRepairNumber(String carsRepairNumber) {
		CarsRepair carsRepair = new CarsRepair();
		carsRepair.setCarsRepairNumber(carsRepairNumber);
		CarsRepair carsRepair1 = carsRepairMapper.selectOne(carsRepair);
		DriverParameter copy = BeanUtils.copy(carsRepair1, DriverParameter.class);
		List<CarsRepair.CarPhoto> listCarPhoto = JSONObject.parseArray(carsRepair1.getCarPhotoJson(), CarsRepair.CarPhoto.class);
		copy.setCarPhoto(listCarPhoto);
		return copy;
	}

	@Override
	public DriverParameter detailsByCarsRepairNumber(String carsRepairNumber) {
		Example carsRepair = new Example(CarsRepair.class);
		Example.Criteria criteriaCarsRepair = carsRepair.createCriteria();
		criteriaCarsRepair.andEqualTo("carsRepairNumber", carsRepairNumber);
		List<CarsRepair> listCarsRepair = carsRepairMapper.selectByExample(carsRepair);
		DriverParameter driverParameter = BeanUtils.copy(listCarsRepair.get(0), DriverParameter.class);
		List<CarsRepair.CarPhoto> listCarPhoto = JSONObject.parseArray(listCarsRepair.get(0).getCarPhotoJson(), CarsRepair.CarPhoto.class);
		driverParameter.setCarPhoto(listCarPhoto);
		return driverParameter;
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
		List<Drivers> list = BeanUtils.copyList(listCarsRepair, Drivers.class);
		list.sort(Comparator.comparing(Drivers::getUpdateTime).reversed());
		PageInfo<Drivers> pageInfo = PageInfo.of(list);
		return new PageData<>(list, pageInfo.getTotal());
	}

	@Override
	public List<String> selectCarNumbers() {
		List<String> strings = carsRepairMapper.selectCarNumbers();
		Collections.reverse(strings);
		return strings;
	}

	@Override
	public List<String> selectCarBrand() {
		List<String> strings = carsRepairMapper.selectCarBrand();
		Collections.reverse(strings);
		return strings;
	}

	@Override
	public List<String> selectPhone() {
		List<String> strings = carsRepairMapper.selectPhone();
		Collections.reverse(strings);
		return strings;
	}

	@Override
	public List<String> selectName() {
		List<String> strings = carsRepairMapper.selectName();
		Collections.reverse(strings);
		return strings;
	}
}
