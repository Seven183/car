package car.paramater;

import car.utils.PageParam;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class InsuranceParameter extends PageParam {

    private Integer insuranceId;
    private String insuranceCompanyName;
    private String insuranceCode;
    private String insuranceUser;
    private String insuranceIdCard;
    private String insurancePhone;
    private Double insuranceAmount;
    private String insuranceCarBrand;
    private String insuranceCarName;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date insuranceStartTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date insuranceEndTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
    private Integer isDelete;
    private String status;
    private String startCreateTime;
    private String endCreateTime;
}
