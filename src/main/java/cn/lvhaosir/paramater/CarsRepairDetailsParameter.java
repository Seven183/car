package cn.lvhaosir.paramater;

import cn.lvhaosir.utils.PageParam;
import lombok.Data;

@Data
public class CarsRepairDetailsParameter extends PageParam {

    private String carNumber;
    private String carsRepairNumber;
    private String carsRepairType;
    private String carsRepairText;
    private String createTime;

    private String advicesType;
    private String advicesName;
    private String advicesNumber;
    private Integer advicesQuantity;
    private Double advicesPriceAmount;
    private Double advicesFullAmount;

    private String userName;
    private Integer age;
    private String sex;
    private String phone;
    private String address;
    private String carBrand;
    private String carName;
    private String engineNumber;

}
