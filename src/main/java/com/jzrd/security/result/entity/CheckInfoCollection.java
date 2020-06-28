package com.jzrd.security.result.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @description: 检查信息合集
 * @author: Dreamer
 * @date: 2020-04-26 10:43
 */
@Data
@ApiModel("collection")
public class CheckInfoCollection implements Serializable {

    /**
     * 检查条目ID
     */

    private int entryId;

    private int objectId;

    private String objectName;

    private String checkPerson;

    private String remark;
    /**
     * 附件地址
     */

    private List<String> attachments;


    List<DetailsList> details;


}
