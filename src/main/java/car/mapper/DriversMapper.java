package car.mapper;

import car.entity.Drivers;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DriversMapper extends CommonMapper<Drivers> {
	
	/**
	 * 模糊查询
	 */
	public List<Drivers> queryLike(Drivers driver);
}