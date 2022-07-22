package car.mapper;


import car.entity.Advices;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AdvicesMapper extends CommonMapper<Advices> {

    @Select("<script>" +
            " select " +
            "     car_number as carNumber " +
            " from (select * from advices where status != 1) t " +
            " group by car_number" +
            " </script>")
    List<String> selectCarNumbers();

    @Select("<script>" +
            " select " +
            "     advices_type as advicesType " +
            " from (select * from advices where status != 1) t " +
            " group by advices_type" +
            " </script>")
    List<String> selectAdvicesType();

    @Select("<script>" +
            " select " +
            "     advices_name as advicesName " +
            " from (select * from advices where status != 1) t " +
            " group by advices_name" +
            " </script>")
    List<String> selectAdvicesName();
}