package cn.lvhaosir.entity;

import cn.lvhaosir.utils.PageParam;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Table(name = "cars_repair")
public class CarsRepair extends PageParam implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "cars_repair_id")
    private Integer carsRepairId;

    @Column(name = "cars_repair_type")
    private String carsRepairType;

    @Column(name = "cars_repair_text")
    private String carsRepairText;

    @Column(name = "car_id")
    private Integer carId;

    @Column(name = "create_time")
    private String createTime;

    @Column(name = "update_time")
    private Date updateTime;

    @Column(name = "status")
    private String status;
}