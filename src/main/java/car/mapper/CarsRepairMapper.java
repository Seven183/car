package car.mapper;

import car.entity.CarsRepair;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CarsRepairMapper extends CommonMapper<CarsRepair> {

    @Select("<script>" +
                "select car_number as carNumber from cars_repair group by car_number order by update_time desc" +
            " </script>")
    List<String> selectCarNumbers();
}