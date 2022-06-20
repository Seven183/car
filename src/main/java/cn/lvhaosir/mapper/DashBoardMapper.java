package cn.lvhaosir.mapper;


import cn.lvhaosir.entity.CarsRepair;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface DashBoardMapper extends CommonMapper<CarsRepair> {

    @Select("<script>" +
            "select sum(advices_full_amount) as advicesFullAmount from cars_repair " +
            " </script>")
    Double selectTotalAmount();
}