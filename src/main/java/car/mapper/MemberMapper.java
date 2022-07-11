package car.mapper;


import car.entity.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MemberMapper extends CommonMapper<Member> {

    @Select("<script>" +
            "select car_number as carNumber from member order by update_time desc" +
            " </script>")
    List<String> selectCarNumbers();
}