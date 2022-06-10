package cn.lvhaosir.service;


import cn.lvhaosir.entity.Drivers;
import cn.lvhaosir.utils.PageData;
import cn.lvhaosir.utils.PageParam;

public interface DriversService{

	public Integer add(Drivers driver);

	public Integer delete(Object id);

	public Integer update(Drivers driver);

	public Drivers selectDriverById(Integer id);

	public PageData<Drivers> queryLikeDrivers(Drivers driver);

	public PageData<Drivers> queryAllDrivers(PageParam pageParam);
}
