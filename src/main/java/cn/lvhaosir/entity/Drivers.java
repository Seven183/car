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
     * 维修编码
     */
    @Column(name = "cars_repair_number")
    private String carsRepairNumber;

    /**
     * 车牌号
     */
    @Column(name = "car_number")
    private String carNumber;

    /**
     * 驾驶人名称
     */
    @Column(name = "user_name")
    private String userName;

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
     * 驾驶人地址
     */
    private String address;

    /**
     * 用户照片
     */
    @Column(name = "user_photo")
    private String userPhoto;

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
     * 结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "end_time")
    private Date endTime;

    /**
     * 是否删除（0：未删除，1：删除）
     */
    @Column(name = "is_delete")
    private Integer isDelete;

    /**
     * 是否维修完成（0：没完成，1：完成）
     */
    @Column(name = "status")
    private Integer status;

    /**
     * 车辆品牌
     */
    @Column(name = "car_brand")
    private String carBrand;

    /**
     * 车辆名称
     */
    @Column(name = "car_name")
    private String carName;

    /**
     * 车架号
     */
    @Column(name = "engine_number")
    private String engineNumber;

    /**
     * 汽车图片
     */
    @Column(name = "car_photo_json")
    private String carPhotoJson;
}