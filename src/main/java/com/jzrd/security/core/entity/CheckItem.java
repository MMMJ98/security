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
 * @description: 检查项表
 * @author: Dreamer
 * @date: 2020-04-23 21:28
 */
@Data
@Slf4j
@TableName("check_item")
@ApiModel("item")
public class CheckItem implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty
    private int itemId;

    @TableField("entry_id")
    private int entryId;

    /**
     * 检查条目名称
     */
    @TableField(exist = false)
    private String entryName;

    @TableField("name")
    private String itemName;

    /**
     * 检查项的类型，主要用于前台判断类型选择输入样式
     */
    @TableField("attr_type")
    private String attrType;

    /**
     * 检查项的扩展内容，根据类型的不同扩展内容也不同
     * 主要用于前台获取供选择的文本
     */
    @TableField("ext_info")
    private String extInfo;

    /**
     * 根据attrType返回默认值
     */
    @TableField(exist = false)
    private String itemResult;
}
