package cn.lvhaosir.mapper;


import cn.lvhaosir.entity.CarsRepair;
import cn.lvhaosir.result.CarBrandPerMonth;
import cn.lvhaosir.result.MoneyPerMonth;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DashBoardMapper extends CommonMapper<CarsRepair> {

    @Select("<script>" +
            " select sum(advices_full_amount) as advicesFullAmount from cars_repair " +
            " </script>")
    Double selectTotalAmount();


    @Select("<script>" +
            " select sum(advices_full_amount) as advicesFullAmount from cars_repair " +
            " where create_time > DATE_SUB(CURDATE(), INTERVAL 365 day) " +
            " </script>")
    Double selectTotalAmountLastYear();


    @Select("<script>" +
            " select " +
            "     sum(advices_full_amount) as money, " +
            "     count(user_name) as user, " +
            "     DATE_FORMAT(create_time,'%Y-%m') as month " +
            " from cars_repair " +
            " where create_time > DATE_SUB(CURDATE(), INTERVAL 365 day) " +
            " group by DATE_FORMAT(create_time,'%Y-%m') " +
            " order by DATE_FORMAT(create_time,'%Y-%m') " +
            " </script>")
    List<MoneyPerMonth> selectAmountLastYearByMonth();


    @Select("<script>" +
            " select " +
            "     count(user_name) as userCount " +
            " from cars_repair " +
            " where create_time > DATE_SUB(CURDATE(), INTERVAL 365 day) " +
            " </script>")
    Integer selectCountUserLastYear();


    @Select("<script>" +
            " select " +
            "     car_brand as name, " +
            "     count(car_brand) as value " +
            " from cars_repair " +
            " where create_time > DATE_SUB(CURDATE(), INTERVAL 365 day) " +
            " group by car_brand " +
            " </script>")
    List<CarBrandPerMonth> selectCarCountByBrandLastYear();



    @Select("<script>" +
            " select " +
            "     count(car_name) as value, " +
            "     car_name as name " +
            " from cars_repair " +
            " where create_time > DATE_SUB(CURDATE(), INTERVAL 30 day) " +
            " group by car_name" +
            " </script>")
    List<CarBrandPerMonth> selectCarCountByBrandAndNameLastMonth();


    @Select("<script>" +
            " select " +
            "     count(car_name) as value, " +
            "     car_name as name " +
            " from cars_repair " +
            " where create_time > DATE_SUB(CURDATE(), INTERVAL 365 day) " +
            " group by car_name" +
            " </script>")
    List<CarBrandPerMonth> selectCarNameAndCountLastYear();

}