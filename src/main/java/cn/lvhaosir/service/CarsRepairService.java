package cn.lvhaosir.service;


import cn.lvhaosir.entity.CarsRepair;
import cn.lvhaosir.utils.PageData;


public interface CarsRepairService {

	public Integer add(CarsRepair carsRepair);

	public Integer delete(Integer id);

	public Integer update(CarsRepair carsRepair);

	public CarsRepair selectCarsRepairById(Integer id);

	public PageData<CarsRepair> queryLikeCarsRepair(CarsRepair carsRepair);

	public PageData<CarsRepair> queryAllCarsRepair(CarsRepair carsRepair);
}
