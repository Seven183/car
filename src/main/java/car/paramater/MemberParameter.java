package car.paramater;

import car.utils.PageParam;
import lombok.Data;

@Data
public class MemberParameter extends PageParam {

    private String carNumber;
    private String address;
    private String memberName;
    private String memberSex;
    private String phone;
    private Integer isDelete;
    private String carBrand;
    private String carName;
    private String engineNumber;
}
