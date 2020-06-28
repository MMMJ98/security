package com.jzrd.security.result.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.Date;

/**
 * @description: 检查结果明细表
 * @author: Dreamer
 * @date: 2020-04-23 22:09
 */
@Data
@Slf4j
@TableName("check_result_detail")
@ApiModel("detail")
public class CheckResultDetail implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private int detailId;

    /**
     * 检查结果表CheckResult的主键ID
     */
    @TableField("result_id")
    private int resultId;

    /**
     * 检查项表CheckItem的主键ID
     */
    @TableField("item_id")
    private int itemId;

    /**
     * 检查项名称
     */
    @TableField(exist = false)
    private String itemName;

    /**
     * 检查结果
     */
    @TableField("item_result")
    private String itemResult;

    /**
     * 检查表ID
     */
    @TableField(exist = false)
    private int sheetId;

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

    /**
     * 检查对象ID
     */
    @TableField(exist = false)
    private int objectId;

    /**
     * 检查对象名称
     */
    @TableField(exist = false)
    private String objectName;

    /**
     * 检查人
     */
    @TableField(exist = false)
    private String checkPerson;

    /**
     * 结果提交时间
     */
    @TableField(exist = false)
    private Date submitTime;

}
