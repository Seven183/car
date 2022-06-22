package cn.lvhaosir.entity;

import cn.lvhaosir.utils.PageParam;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Table(name = "advices")
public class Advices extends PageParam implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "advices_id")
    private Integer advicesId;

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
     * 设备集合
     */
    @Column(name = "items")
    private String items;

    /**
     * 设备类型
     */
    @Column(name = "advices_type")
    private String advicesType;

    /**
     * 设备名称
     */
    @Column(name = "advices_name")
    private String advicesName;

    /**
     * 设备编号（sn 码）
     */
    @Column(name = "advices_number")
    private String advicesNumber;

    /**
     * 设备数量
     */
    @Column(name = "advices_quantity")
    private Integer advicesQuantity;

    /**
     * 设备单价金额
     */
    @Column(name = "advices_price_amount")
    private Double advicesPriceAmount;

    /**
     * 设备总金额
     */
    @Column(name = "advices_full_amount")
    private Double advicesFullAmount;

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
}