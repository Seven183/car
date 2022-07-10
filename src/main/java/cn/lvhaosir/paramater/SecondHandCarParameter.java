package cn.lvhaosir.paramater;

import cn.lvhaosir.utils.PageParam;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Data
public class SecondHandCarParameter extends PageParam {

    private Integer secondHandCarId;
    private String buyerUser;
    private String buyerIdCard;
    private String buyerPhone;
    private String buyerAge;
    private String buyerSex;
    private Integer buyerAddress;
    private String sellerUser;
    private String sellerIdCard;
    private String sellerPhone;
    private String sellerAge;
    private String sellerSex;
    private String sellerAddress;
    private String secondHandCarAmount;
    private String secondHandCarBrand;
    private String secondHandCarName;
    private String secondHandCarEngineNumber;
    private String secondHandCarPhotoJson;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
    private Integer isDelete;
    private String status;
    private String startCreateTime;
    private String endCreateTime;
}
