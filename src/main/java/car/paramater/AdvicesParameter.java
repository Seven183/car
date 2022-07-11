package car.paramater;

import car.utils.PageParam;
import lombok.Data;

@Data
public class AdvicesParameter extends PageParam {

    private String advicesId;
    private String carNumber;
    private String advicesType;
    private String advicesName;
    private String advicesNumber;
    private Integer isDelete;
    private String status;
}
