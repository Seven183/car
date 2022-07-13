package car.paramater;

import car.entity.CarsRepair;
import car.utils.PageParam;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class SecondHandCarParameter extends PageParam {

    private Integer secondHandCarId;
    private String buyerUser;
    private String buyerIdCard;
    private String buyerPhone;
    private String buyerAge;
    private String buyerSex;
    private String buyerAddress;
    private String sellerUser;
    private String sellerIdCard;
    private String sellerPhone;
    private String sellerAge;
    private String sellerSex;
    private String sellerAddress;
    private String secondHandCarNumber;
    private String secondHandCarAmount;
    private String secondHandCarBrand;
    private String secondHandCarName;
    private String secondHandCarEngineNumber;
    private List<CarsRepair.CarPhoto> carPhoto;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
    private Integer isDelete;
    private String status;
    private String startCreateTime;
    private String endCreateTime;
}
