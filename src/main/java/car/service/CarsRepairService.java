package car.service;


import car.entity.CarsRepair;
import car.paramater.CarsRepairParameter;
import car.utils.PageData;

import java.text.ParseException;
import java.util.List;


public interface CarsRepairService {

	public Integer add(CarsRepairParameter carsRepair);

	public Integer delete(String carsRepairNumber);

	public Integer update(CarsRepairParameter carsRepair);

	public CarsRepairParameter selectCarsRepairByCarsRepairNumber(String carsRepairNumber);

	public PageData<CarsRepair> queryAllCarsRepairs(CarsRepairParameter carsRepairParameter) throws ParseException;

	public Integer statusOperate(String carsRepairNumber, Integer status);

	public CarsRepairParameter detailsByCarsRepairNumber(String carsRepairNumber);

	public List<String> selectCarNumbers();

	public List<String> selectCarsRepairType();

	public List<String> selectPhone();
}
