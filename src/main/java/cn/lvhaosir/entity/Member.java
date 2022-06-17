package cn.lvhaosir.entity;

import cn.lvhaosir.utils.PageParam;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Table(name = "member")
public class Member extends PageParam implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Integer memberId;

    /**
     * 会员名称
     */
    @Column(name = "member_name")
    private String memberName;

    /**
     * 车牌号
     */
    @Column(name = "car_number")
    private String carNumber;

    /**
     * 会员性别
     */
    @Column(name = "member_sex")
    private String memberSex;

    /**
     * 会员年龄
     */
    private Integer age;

    /**
     * 会员地址
     */
    private String address;

    /**
     * 会员联系方式
     */
    @Column(name = "phone")
    private String phone;

    /**
     * 会员照片
     */
    private String photo;

    /**
     * 会员充值金额
     */
    @Column(name = "recharge_amount")
    private Double rechargeAmount;

    /**
     * 会员剩余金额
     */
    @Column(name = "remain_amount")
    private Double remainAmount;

    /**
     * 会员创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 会员更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 会员状态（0：注册会员，1：解除会员）
     */
    @Column(name = "status")
    private Integer status;

    /**
     * 是否删除（0：未删除，1：删除）
     */
    @Column(name = "is_delete")
    private Integer isDelete;
}