package car.mapper;


import car.entity.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MemberMapper extends CommonMapper<Member> {

    @Select("<script>" +
            "select car_number as carNumber from member where is_delete = 0 order by update_time desc " +
            " </script>")
    List<String> selectCarNumbers();

    @Select("<script>" +
            "select car_brand as carBrand from  ( select car_brand from  member where is_delete = 0 ) t group by car_brand " +
            " </script>")
    List<String> selectCarBrands();

    @Select("<script>" +
            "select phone from  ( select phone from  member where is_delete = 0 ) t group by phone " +
            " </script>")
    List<String> selectMemberPhones();

    @Select("<script>" +
            "select member_name as memberName from ( select member_name from  member where is_delete = 0 ) t group by member_name " +
            " </script>")
    List<String> selectMemberUsers();
}