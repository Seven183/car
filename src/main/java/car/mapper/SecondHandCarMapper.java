package car.mapper;


import car.entity.SecondHandCar;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SecondHandCarMapper extends CommonMapper<SecondHandCar> {

    @Select("<script>" +
            "select second_hand_car_number as secondHandCarNumber from second_hand_car group by second_hand_car_number order by update_time desc" +
            " </script>")
    List<String> selectCarNumbers();
}