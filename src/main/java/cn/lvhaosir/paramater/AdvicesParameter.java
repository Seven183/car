package cn.lvhaosir.paramater;

import cn.lvhaosir.utils.PageParam;
import lombok.Data;

@Data
public class AdvicesParameter extends PageParam {

    private String carNumber;
    private String advicesType;
    private String advicesName;
    private String advicesNumber;
    private Integer isDelete;
    private String status;
}
