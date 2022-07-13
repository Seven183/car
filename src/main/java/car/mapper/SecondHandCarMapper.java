package car.mapper;


import car.entity.SecondHandCar;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SecondHandCarMapper extends CommonMapper<SecondHandCar> {

    @Select("<script>" +
            " select " +
            "     second_hand_car_number as secondHandCarNumber " +
            " from second_hand_car " +
            " group by second_hand_car_number " +
            " </script>")
    List<String> selectCarNumbers();

    @Select("<script>" +
            " select " +
            "     second_hand_car_brand as secondHandCarBrand " +
            " from second_hand_car " +
            " group by second_hand_car_brand " +
            " </script>")
    List<String> selectCarBrands();

    @Select("<script>" +
            " select " +
            "     buyer_phone as buyerPhone " +
            " from second_hand_car " +
            " group by buyerPhone " +
            " </script>")
    List<String> selectBuyerPhones();

    @Select("<script>" +
            " select " +
            "     buyer_user as buyerUser " +
            " from second_hand_car " +
            " group by buyerUser " +
            " </script>")
    List<String> selectBuyerUsers();

    @Select("<script>" +
            " select " +
            "     buyer_id_card as buyerIdCard " +
            " from second_hand_car " +
            " group by buyerIdCard " +
            " </script>")
    List<String> selectBuyerIdCards();
}