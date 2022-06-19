package cn.lvhaosir.service;


import cn.lvhaosir.entity.CarsRepair;
import cn.lvhaosir.paramater.CarsRepairParameter;
import cn.lvhaosir.result.CarsRepairDetails;
import cn.lvhaosir.utils.PageData;

import java.text.ParseException;
import java.util.Set;


public interface CarsRepairService {

	public Integer add(CarsRepair carsRepair);

	public Integer delete(Integer id);

	public Integer update(CarsRepair carsRepair);

	public CarsRepair selectCarsRepairById(Integer id);

	public PageData<CarsRepair> queryAllCarsRepairs(CarsRepairParameter carsRepairParameter) throws ParseException;

	public Set<String> selectCarNumbers();

	public Integer statusOperate(Integer carsRepairId, Integer status);

	public CarsRepairDetails detailsByCarNumber(String carNumber);
}
