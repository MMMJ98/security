package com.jzrd.security.result.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @description:
 * @author: Dreamer
 * @date: 2020-05-03 9:12
 */
@Data
public class DetailsList implements Serializable {

    /**
     * 检查条目ID
     */
    private int entryId;


    /**
     * 检查条目名称
     */
    private String entryName;

    /**
     * 检查项ID
     */
    private int itemId;

    /**
     * 检查项名称
     */
    private String itemName;


    /**
     * 检查项结果
     */
    private String itemResult;

    /**
     * 属性类型（e 枚举  n 数字  t 文本）
     */
    private String attrType;

    /**
     * 附加信息
     */
    private String extInfo;
}
