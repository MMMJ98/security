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
 * @description: 检查条目表
 * @author: Dreamer
 * @date: 2020-04-23 21:01
 */
@Data
@Slf4j
@TableName("check_entry")
@ApiModel("entry")
public class CheckEntry implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer entryId;

    /**
     * 检查表CheckSheet的主键ID
     */
    @TableField("sheet_id")
    private int sheetId;

    /**
     * 检查表名称
     */
    @TableField(exist = false)
    private String sheetName;

    @TableField("name")
    private String entryName;

    /**
     * 检查频率
     */
    @TableField("frequency")
    private String frequency;

    /**
     * 检查部门
     */
    @TableField("department")
    private String department;

    /**
     * 检查条目在具体某一检查表下的顺序
     */
    @TableField("order_no")
    private int orderNo;

    /**
     * 检查对象类型表ObjectType的主键ID
     */
    @TableField("object_type_id")
    private int objectTypeId;

    /**
     * 检查对象类型表ObjectType的名称name
     */
    @TableField(exist = false)
    private String objectTypeName;
}
