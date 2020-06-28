package com.jzrd.security.result.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.*;
import com.alibaba.excel.metadata.BaseRowModel;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.wuwenze.poi.annotation.Excel;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @description: 用于查询检查结果的实体
 * @author: Dreamer
 * @date: 2020-05-13 10:37
 */
@Data
@Slf4j
@ApiModel("queryCollection")
@Excel("检查结果查询表")
@ContentRowHeight(30)
@HeadRowHeight(40)
@ColumnWidth(25)
// 头字体设置成14
@HeadFontStyle(fontHeightInPoints = 14)
// 内容字体设置成12
@ContentFontStyle(fontHeightInPoints = 12)
@ContentStyle(horizontalAlignment = HorizontalAlignment.CENTER,verticalAlignment = VerticalAlignment.CENTER,wrapped = true)
public class QueryCollection implements Serializable {

    /**
     * 检查表ID
     *  忽略字段
     */
    @ExcelIgnore
    private int sheetId;

    @ExcelProperty("检查表名")
    @ColumnWidth(40)
    private String sheetName;

    /**
     * 检查条目ID
     */
    @ExcelIgnore
    private int entryId;

    /**
     * 检查条目名称
     */
    @ExcelProperty("检查条目名称")
    @ColumnWidth(60)
    private String entryName;

    /**
     * 检查对象ID
     */
    @ExcelIgnore
    private int objectId;

    /**
     * 检查对象名称
     */
    @ExcelProperty("检查对象名称")
    private String objectName;

    /**
     * 检查项表CheckItem的主键ID
     */
    @ExcelIgnore
    private int itemId;

    /**
     * 检查项名称
     */
    @ExcelProperty("检查项名称")
    private String itemName;

    /**
     * 检查结果
     */
    @ExcelProperty("检查结果")
    private String itemResult;

    /**
     * 检查频率
     */
    @ExcelProperty("检查频率")
    private String frequency;

    /**
     * 检查部门
     */
    @ExcelProperty("检查部门")
    private String department;

    /**
     * 检查人
     */
    @ExcelProperty("检查人")
    private String checkPerson;

    /**
     * 结果提交时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @ExcelProperty("检查时间")
    private String submitTime;

    @ExcelProperty("备注")
    private String remark;
    @ExcelIgnore
    private String submitTimeFrom;

    @ExcelIgnore
    private String submitTimeTo;

    /**
     * 结果明细表CheckResultDetail的主键ID
     */
    @ExcelIgnore
    private int detailId;

    /**
     * 检查结果表CheckResult的主键ID
     */
    @ExcelIgnore
    private int resultId;

    @ExcelIgnore
    private int attachmentId;

    /**
     * 附件地址
     */
    @ExcelIgnore
    private String url;

}
