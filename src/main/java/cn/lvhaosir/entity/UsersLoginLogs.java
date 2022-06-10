package cn.lvhaosir.entity;

import cn.lvhaosir.utils.PageParam;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Table(name = "users_login_logs")
public class UsersLoginLogs extends PageParam implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "login_time")
    private Date loginTime;

    @Column(name = "create_time")
    private Date createTime;
}