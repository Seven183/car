package cn.lvhaosir.paramater;

import cn.lvhaosir.utils.PageParam;
import lombok.Data;

@Data
public class DriverParameter extends PageParam {

    private String carNumber;
    private String driverName;
    private String sex;
    private String phone;
    private String address;
    private Integer isDelete;
    private String carBrand;
    private String carName;
    private String engineNumber;
}
