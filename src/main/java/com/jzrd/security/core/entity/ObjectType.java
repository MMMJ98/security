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
 * @description: 检查对象类型表
 * @author: Dreamer
 * @date: 2020-04-23 21:37
 */
@Data
@Slf4j
@TableName("object_type")
@ApiModel("type")
public class ObjectType implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private int typeId;

    @TableField("name")
    private String typeName;

    /**
     * 检查条目ID
     */
    @TableField(exist = false)
    private int entryId;

    /**
     * 检查条目名称
     */
    @TableField(exist = false)
    private String entryName;
}
