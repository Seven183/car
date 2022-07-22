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
                " from (select * from cars_repair where status != 1) t " +
                " group by car_number" +
            " </script>")
    List<String> selectCarNumbers();

    @Select("<script>" +
            " select " +
            "     cars_repair_type as carsRepairType " +
            " from (select * from cars_repair where status != 1) t " +
            " group by cars_repair_type" +
            " </script>")
    List<String> selectCarsRepairType();

    @Select("<script>" +
            " select " +
            "     phone as phone " +
            " from (select * from cars_repair where status != 1) t " +
            " group by phone" +
            " </script>")
    List<String> selectPhone();

    @Select("<script>" +
            " select " +
            "     car_brand as carBrand " +
            " from (select * from cars_repair where status != 1) t " +
            " group by car_brand" +
            " </script>")
    List<String> selectCarBrand();

    @Select("<script>" +
            " select " +
            "     user_name as userName " +
            " from (select * from cars_repair where status != 1) t " +
            " group by user_name" +
            " </script>")
    List<String> selectName();
}