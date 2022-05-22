package io.qingyu.shop.bean;

import com.baomidou.mybatisplus.annotation.*;
import io.qingyu.shop.utils.id.SnowFlakeFactory;
import io.qingyu.shop.utils.psswd.PasswordUtils;
import lombok.Data;

import java.io.Serializable;

/**
 * @author qingYu
 * @version 1.0.0
 * @Description 用户类
 */
@Data
@TableName("t_user")
public class User implements Serializable {
    /**
     * 数据id
     */
    @TableId(value = "id", type = IdType.INPUT)
    @TableField(value = "id", fill = FieldFill.INSERT)
    private Long id;

    /**
     * 用户名
     */
    @TableField("t_username")
    private String username;

    /**
     * 密码
     */
    @TableField("t_password")
    private String password;

    /**
     * 手机号
     */
    @TableField("t_phone")
    private String phone;

    /**
     * 地址
     */
    @TableField("t_address")
    private String address;

    public User(){
        this.id = SnowFlakeFactory.getSnowFlakeFromCache().nextId();
        //默认密码
        this.password = PasswordUtils.getPassword("qingyu");
    }
}
