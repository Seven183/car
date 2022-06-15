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

    @Column(name = "advices_type")
    private String advicesType;

    @Column(name = "advices_name")
    private String advicesName;

    @Column(name = "advices_number")
    private String advicesNumber;

    @Column(name = "advices_quantity")
    private Integer advicesQuantity;

    @Column(name = "advices_price_amount")
    private Double advicesPriceAmount;

    @Column(name = "advices_full_amount")
    private Double advicesFullAmount;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "create_time")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "update_time")
    private Date updateTime;

    private String status;
}