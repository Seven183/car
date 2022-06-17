package cn.lvhaosir.entity;

import cn.lvhaosir.utils.PageParam;
import com.fasterxml.jackson.annotation.JsonFormat;
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

    /**
     * 品牌
     */
    @Column(name = "car_brand")
    private String carBrand;

    /**
     * 名称
     */
    @Column(name = "car_name")
    private String carName;

    /**
     * 车牌号码
     */
    @Column(name = "car_number")
    private String carNumber;

    /**
     * 车架号
     */
    @Column(name = "engine_number")
    private String engineNumber;

    /**
     * 驾驶人ID
     */
    @Column(name = "driver_id")
    private Integer driverId;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 汽车图片
     */
    private String photo;

    /**
     * 状态
     */
    @Column(name = "status")
    private Integer status;

    /**
     * 是否删除（0：未删除，1：删除）
     */
    @Column(name = "is_delete")
    private Integer isDelete;
}