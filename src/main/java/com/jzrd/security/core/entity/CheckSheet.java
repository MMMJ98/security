package com.jzrd.security.core.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * @description: 检查表
 * @author: Dreamer
 * @date: 2020-04-23 16:17
 */
@Data
@Slf4j
@TableName("check_sheet")
@ApiModel("sheet")
public class CheckSheet implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty
    private int sheetId;

    @TableField("name")
    private String sheetName;

    @TableField("order_no")
    private int orderNo;

    /**
     * 备注
     */
    @TableField("remark")
    private String remark;
}
