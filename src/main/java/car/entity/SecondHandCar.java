package car.entity;

import car.utils.PageParam;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Table(name = "second_hand_car")
public class SecondHandCar extends PageParam implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "second_hand_car_id")
    private Integer secondHandCarId;


    /**
     * 买家
     */
    @Column(name = "buyer_user")
    private String buyerUser;

    /**
     * 买家身份证号
     */
    @Column(name = "buyer_id_card")
    private String buyerIdCard;


    /**
     * 买家手机号
     */
    @Column(name = "buyer_phone")
    private String buyerPhone;


    /**
     * 买家年龄
     */
    @Column(name = "buyer_age")
    private String buyerAge;


    /**
     * 买家性别
     */
    @Column(name = "buyer_sex")
    private String buyerSex;


    /**
     * 买家地址
     */
    @Column(name = "buyer_address")
    private String buyerAddress;


    /**
     * 卖家
     */
    @Column(name = "seller_user")
    private String sellerUser;


    /**
     * 卖家身份证号
     */
    @Column(name = "seller_id_card")
    private String sellerIdCard;

    /**
     * 卖家手机号
     */
    @Column(name = "seller_phone")
    private String sellerPhone;

    /**
     * 卖家年龄
     */
    @Column(name = "seller_age")
    private String sellerAge;

    /**
     * 卖家性别
     */
    @Column(name = "seller_sex")
    private String sellerSex;

    /**
     * 卖家地址
     */
    @Column(name = "seller_address")
    private String sellerAddress;

    /**
     * 二手车金额
     */
    @Column(name = "second_hand_car_amount")
    private Double secondHandCarAmount;

    /**
     * 二手车品牌
     */
    @Column(name = "second_hand_car_brand")
    private String secondHandCarBrand;

    /**
     * 二手车车牌号
     */
    @Column(name = "second_hand_car_number")
    private String secondHandCarNumber;

    /**
     * 二手车名称
     */
    @Column(name = "second_hand_car_name")
    private String secondHandCarName;

    /**
     * 二手车车架号
     */
    @Column(name = "second_hand_car_engine_number")
    private String secondHandCarEngineNumber;

    /**
     * 二手车图片
     */
    @Column(name = "second_hand_car_photoJson")
    private String secondHandCarPhotoJson;


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
     * 是否删除（0：未删除，1：删除）
     */
    @Column(name = "is_delete")
    private Integer isDelete;

    /**
     * 状态（0：没完成，1：完成）
     */
    @Column(name = "status")
    private Integer status;
}