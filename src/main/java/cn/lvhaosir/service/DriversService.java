package cn.lvhaosir.service;


import cn.lvhaosir.entity.Drivers;
import cn.lvhaosir.paramater.DriverParameter;
import cn.lvhaosir.utils.PageData;

public interface DriversService{

	public Integer add(Drivers driver);

	public Integer delete(Integer driverId);

	public Integer update(Drivers driver);

	public Drivers selectDriverById(Integer driverId);

	public Drivers selectDriverByCarsRepairNumber(String carsRepairNumber);

	public PageData<Drivers> queryLikeDrivers(Drivers driver);

	public PageData<Drivers> allDrivers(DriverParameter driverParameter);
}
