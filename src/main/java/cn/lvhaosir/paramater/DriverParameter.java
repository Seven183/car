package cn.lvhaosir.paramater;

import cn.lvhaosir.entity.CarsRepair;
import cn.lvhaosir.utils.PageParam;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
    private Date createTime;
    private Date updateTime;
    private Date endTime;
    private Integer isDelete;
    private String status;
    private String carBrand;
    private String carName;
    private String engineNumber;
    private List<CarsRepair.CarPhoto> carPhoto;
}
