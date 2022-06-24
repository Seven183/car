package cn.lvhaosir.service;


import cn.lvhaosir.entity.Drivers;
import cn.lvhaosir.paramater.DriverParameter;
import cn.lvhaosir.utils.PageData;

public interface DriversService{

	public Integer update(Drivers driver);

	public Drivers selectDriverByCarsRepairNumber(String carsRepairNumber);

	public PageData<Drivers> allDrivers(DriverParameter driverParameter);
}
