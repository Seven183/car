package cn.lvhaosir.entity;

import cn.lvhaosir.utils.PageParam;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Table(name = "cars")
public class Cars extends PageParam implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_id")
    private Integer carId;

    @Column(name = "car_number")
    private String carNumber;

    @Column(name = "engine_number")
    private String engineNumber;

    @Column(name = "start_time")
    private String startTime;

    @Column(name = "end_time")
    private String endTime;

    @Column(name = "driver_id")
    private Integer driverId;

    @Column(name = "create_time")
    private Date createTime;

    private String photo;

    @Column(name = "status")
    private String status;
}