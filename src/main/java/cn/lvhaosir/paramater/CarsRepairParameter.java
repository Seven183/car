package cn.lvhaosir.paramater;

import cn.lvhaosir.entity.CarsRepair;
import cn.lvhaosir.utils.PageParam;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class CarsRepairParameter extends PageParam {

    private Integer carsRepairId;
    private String carsRepairNumber;
    private String carNumber;
    private String carsRepairType;
    private String carsRepairText;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endTime;
    private String status;
    private Integer isDelete;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
    private String userName;
    private String phone;
    private Integer age;
    private String sex;
    private String address;
    private String userPhoto;
    private String carBrand;
    private String carName;
    private String engineNumber;
    private List<CarsRepair.CarPhoto> carPhoto;
    private List<CarsRepair.Advices> advicesItems;
    private String startCreateTime;
    private String endCreateTime;

}
