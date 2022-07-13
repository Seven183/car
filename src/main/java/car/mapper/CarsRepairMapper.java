package car.mapper;

import car.entity.CarsRepair;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CarsRepairMapper extends CommonMapper<CarsRepair> {

    @Select("<script>" +
                " select " +
                "     car_number as carNumber " +
                " from cars_repair " +
                " group by car_number" +
            " </script>")
    List<String> selectCarNumbers();

    @Select("<script>" +
            " select " +
            "     cars_repair_type as carsRepairType " +
            " from cars_repair " +
            " group by cars_repair_type" +
            " </script>")
    List<String> selectCarsRepairType();
}