package car.entity;

import car.utils.PageParam;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Table(name = "insurance")
public class Insurance extends PageParam implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "insurance_id")
    private Integer insuranceId;

    /**
     * 保险公司名称
     */
    @Column(name = "insurance_company_name")
    private String insuranceCompanyName;

    /**
     * 保险单号
     */
    @Column(name = "insurance_code")
    private String insuranceCode;

    /**
     * 被保险人
     */
    @Column(name = "insurance_user")
    private String insuranceUser;

    /**
     * 被保险人身份证号
     */
    @Column(name = "insurance_id_card")
    private String insuranceIdCard;

    /**
     * 保险人手机号
     */
    @Column(name = "insurance_phone")
    private String insurancePhone;

    /**
     * 保险金额
     */
    @Column(name = "insurance_amount")
    private Integer insuranceAmount;

    /**
     * 保险车辆品牌
     */
    @Column(name = "insurance_car_brand")
    private Double insuranceCarBrand;

    /**
     * 保险车辆名称
     */
    @Column(name = "insurance_car_name")
    private Double insuranceCarName;

    /**
     * 保险开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "insurance_start_time")
    private Date insuranceStartTime;

    /**
     * 保险到期时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "insurance_end_time")
    private Date insuranceEndTime;

    /**
     * 保险记录创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 保险记录更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "update_time")
    private Date updateTime;

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