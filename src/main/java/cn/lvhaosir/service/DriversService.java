package cn.lvhaosir.service;


import cn.lvhaosir.entity.Drivers;
import cn.lvhaosir.paramater.DriverParameter;
import cn.lvhaosir.utils.PageData;

public interface DriversService{

	public Integer add(Drivers driver);

	public Integer delete(Object driverId);

	public Integer update(Drivers driver);

	public Drivers selectDriverById(Integer driverId);

	public PageData<Drivers> queryLikeDrivers(Drivers driver);

	public PageData<Drivers> allDrivers(DriverParameter driverParameter);
}
