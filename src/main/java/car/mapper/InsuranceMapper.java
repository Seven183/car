package car.mapper;


import car.entity.Insurance;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface InsuranceMapper extends CommonMapper<Insurance> {

    @Select("<script>" +
            " select " +
            "     insurance_company_name as insuranceCompanyName " +
            " from insurance " +
            " group by insuranceCompanyName " +
            " </script>")
    List<String> selectInsuranceCompanyName();

    @Select("<script>" +
            " select " +
            "     insurance_code as insuranceCode " +
            " from insurance " +
            " group by insuranceCode " +
            " </script>")
    List<String> selectInsuranceCode();

    @Select("<script>" +
            " select " +
            "     insurance_user as insuranceUser " +
            " from insurance " +
            " group by insuranceUser " +
            " </script>")
    List<String> selectInsuranceUser();

    @Select("<script>" +
            " select " +
            "     insurance_id_card as insuranceIdCard " +
            " from insurance " +
            " group by insuranceIdCard " +
            " </script>")
    List<String> selectInsuranceIdCard();

    @Select("<script>" +
            " select " +
            "     insurance_phone as insurancePhone " +
            " from insurance " +
            " group by insurancePhone " +
            " </script>")
    List<String> selectInsurancePhone();
}