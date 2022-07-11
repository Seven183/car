package car.paramater;

import car.entity.CarsRepair;
import car.utils.PageParam;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class DriverParameter extends PageParam {

    private String driverName;
    private Integer driverId;
    private String carsRepairNumber;
    private String carNumber;
    private String userName;
    private Integer age;
    private String sex;
    private String phone;
    private String address;
    private String userPhoto;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endTime;
    private Integer isDelete;
    private String status;
    private String carBrand;
    private String carName;
    private String engineNumber;
    private List<CarsRepair.CarPhoto> carPhoto;
}
