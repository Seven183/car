package cn.lvhaosir.result;

import cn.lvhaosir.entity.Advices;
import cn.lvhaosir.entity.CarsRepair;
import cn.lvhaosir.entity.Drivers;
import lombok.Data;

import java.util.List;

@Data
public class CarsRepairDetails {

    private List<CarsRepair> carsRepair;
    private List<Advices> advices;
    private List<Drivers> drivers;

    private String carsRepairNumber;
    private String carNumber;
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
