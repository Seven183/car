package cn.lvhaosir.paramater;

import cn.lvhaosir.utils.PageParam;
import lombok.Data;

@Data
public class CarParameter extends PageParam {

    private String carBrand;
    private String carName;
    private String carNumber;
    private String engineNumber;
}
