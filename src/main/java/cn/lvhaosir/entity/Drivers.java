package cn.lvhaosir.entity;

import cn.lvhaosir.utils.PageParam;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Table(name = "drivers")
public class Drivers extends PageParam implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "driver_id")
    private Integer driverId;

    /**
     * 车牌号
     */
    @Column(name = "car_number")
    private String carNumber;

    /**
     * 驾驶人名称
     */
    @Column(name = "driver_name")
    private String driverName;

    /**
     * 驾驶人年龄
     */
    private Integer age;

    /**
     * 驾驶人性别
     */
    private String sex;

    /**
     * 驾驶人电话
     */
    private String phone;

    /**
     * 驾驶人车辆iD
     */
    @Column(name = "id_card")
    private String idCard;

    /**
     * 驾驶人地址
     */
    private String address;

    /**
     * 驾驶人照片
     */
    private String photo;

    /**
     * 创建时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 是否删除（0：未删除，1：删除）
     */
    @Column(name = "is_delete")
    private Integer isDelete;
}