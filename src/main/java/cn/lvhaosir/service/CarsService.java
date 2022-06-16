package cn.lvhaosir.service;

import cn.lvhaosir.entity.Cars;
import cn.lvhaosir.paramater.CarParameter;
import cn.lvhaosir.utils.PageData;

public interface CarsService {

	public Integer add(Cars car);

	public Integer delete(Integer id);

	public Integer update(Cars car);

	public Cars selectCarById(Integer id);

	public PageData<Cars> queryLikeCars(Cars car);

	public PageData<Cars> allCars(CarParameter carParameter);

}
