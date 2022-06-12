package cn.lvhaosir.paramater;

import cn.lvhaosir.utils.PageParam;
import lombok.Data;

import java.util.Arrays;

@Data
public class CarsRepairParameter extends PageParam {

    private String userName;
    private String phone;
    private String carNumber;
    private Arrays[] createTimeArray;
}
