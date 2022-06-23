package cn.lvhaosir.service;


import cn.lvhaosir.entity.CarsRepair;
import cn.lvhaosir.paramater.CarsRepairParameter;
import cn.lvhaosir.utils.PageData;

import java.text.ParseException;
import java.util.Set;


public interface CarsRepairService {

	public Integer add(CarsRepairParameter carsRepair);

	public Integer delete(String carsRepairNumber);

	public Integer update(CarsRepairParameter carsRepair);

	public CarsRepairParameter selectCarsRepairByCarsRepairNumber(String carsRepairNumber);

	public PageData<CarsRepair> queryAllCarsRepairs(CarsRepairParameter carsRepairParameter) throws ParseException;

	public Set<String> selectCarNumbers();

	public Integer statusOperate(String carsRepairNumber, Integer status);

	public CarsRepairParameter detailsByCarsRepairNumber(String carsRepairNumber);
}
