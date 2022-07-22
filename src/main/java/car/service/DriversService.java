package car.service;


import car.entity.Drivers;
import car.paramater.DriverParameter;
import car.utils.PageData;

import java.util.List;

public interface DriversService{

	public Integer update(Drivers driver);

	public DriverParameter selectDriverByCarsRepairNumber(String carsRepairNumber);

	public PageData<Drivers> allDrivers(DriverParameter driverParameter);

	public DriverParameter detailsByCarsRepairNumber(String carsRepairNumber);

	public List<String> selectCarNumbers();

	public List<String> selectPhone();

	public List<String> selectCarBrand();

	public List<String> selectName();
}
