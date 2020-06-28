package com.jzrd.security.result.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.Date;

/**
 * @description:
 * @author: Dreamer
 * @date: 2020-04-23 16:19
 */
@Data
@Slf4j
@TableName("check_result")
@ApiModel("sec")
public class CheckResult implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private int resultId;

    /**
     * 检查条目表CheckEntry的主键ID
     */
    @TableField("entry_id")
    private int entryId;

    /**
     * 检查对象表CheckObject的主键ID
     */
    @TableField("object_id")
    private int objectId;

    @TableField("object_name")
    private String objectName;

    /**
     * 检查人
     */
    @TableField("check_person")
    private String checkPerson;

    /**
     * 提交时间
     */
    @TableField("submit_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date submitTime;

    /**
     * 备注
     */
    @TableField("remark")
    private String remark;
}
