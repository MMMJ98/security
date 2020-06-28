package com.jzrd.security.core.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * @description: 检查对象表
 * @author: Dreamer
 * @date: 2020-04-23 21:40
 */
@Data
@Slf4j
@TableName("check_object")
@ApiModel("object")
public class CheckObject implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private int objectId;

    /**
     * 检查对象类型表ObjectType的主键ID
     */
    @TableField("object_type_id")
    private int typeId;

    /**
     * 检查对象类型名称
     */
    @TableField(exist = false)
    private String typeName;

    @TableField("name")
    private String objectName;

}
