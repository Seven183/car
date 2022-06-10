package cn.lvhaosir.mapper;

import cn.lvhaosir.entity.Cars;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CarsMapper extends CommonMapper<Cars> {
	
	/**
	 * 模糊查询
	 * @param car
	 * @return
	 */
	public List<Cars> queryLike(Cars car);
	
}