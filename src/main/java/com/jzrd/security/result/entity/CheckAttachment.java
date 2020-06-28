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

/**
 * @description: 检查附件表
 * @author: Dreamer
 * @date: 2020-04-23 22:12
 */
@Data
@Slf4j
@TableName("check_attachment")
@ApiModel("attachment")
public class CheckAttachment implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private int attachmentId;

    /**
     * 附件存储路径
     */
    @TableField("url")
    private String url;

    /**
     * 检查结果表CheckResult的主键ID
     */
    @TableField("result_id")
    private int resultId;
}
