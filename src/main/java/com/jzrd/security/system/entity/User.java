package com.jzrd.security.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @description:
 * @author: Dreamer
 * @date: 2020-05-16 19:59
 */
@Slf4j
@Data
@TableName("check_user")
public class User {

    @TableId(value = "id", type = IdType.AUTO)
    private int id;

    @TableField("name")
    private String userName;

    @TableField("password")
    private String password;

    @TableField("department")
    private String department;
}
