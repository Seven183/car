package cn.lvhaosir.entity;

import cn.lvhaosir.utils.PageParam;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Table(name = "member")
public class Member extends PageParam implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "member_id")
    private Integer memberId;

    @Column(name = "member_name")
    private String memberName;

    @Column(name = "phone")
    private String phone;

    @Column(name = "recharge_amount")
    private Double rechargeAmount;

    @Column(name = "remain_amount")
    private Double remainAmount;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    @Column(name = "status")
    private String status;
}