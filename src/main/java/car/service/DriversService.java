package car.service;


import car.entity.Drivers;
import car.paramater.DriverParameter;
import car.utils.PageData;

public interface DriversService{

	public Integer update(Drivers driver);

	public DriverParameter selectDriverByCarsRepairNumber(String carsRepairNumber);

	public PageData<Drivers> allDrivers(DriverParameter driverParameter);

	public DriverParameter detailsByCarsRepairNumber(String carsRepairNumber);
}
