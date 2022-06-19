package cn.lvhaosir.paramater;

import cn.lvhaosir.utils.PageParam;
import lombok.Data;

@Data
public class CarsRepairParameter extends PageParam {

    private String carsRepairType;
    private String userName;
    private String phone;
    private String carNumber;
    private String startCreateTime;
    private String endCreateTime;
    private Integer isDelete;
    private String status;
}
