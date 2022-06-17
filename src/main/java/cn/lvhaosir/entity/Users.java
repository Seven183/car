package cn.lvhaosir.entity;

import cn.lvhaosir.utils.PageParam;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Table(name = "users")
public class Users extends PageParam implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户ID
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 用户名
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * 密码
     */
    private String pwd;

    /**
     * 别名
     */
    @Column(name = "nick_name")
    private String nickName;

    /**
     * 用户角色
     */
    @Column(name = "user_admin")
    private String userAdmin;

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
     * 会员状态
     */
    @Column(name = "status")
    private Integer status;

    /**
     * 是否删除（0：未删除，1：删除）
     */
    @Column(name = "is_delete")
    private Integer isDelete;
}