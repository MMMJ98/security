package com.jzrd.security.result.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jzrd.security.core.service.ICheckSheetService;
import com.jzrd.security.result.entity.CheckAttachment;
import com.jzrd.security.result.entity.QueryCollection;
import com.jzrd.security.result.service.ICheckAttachmentService;
import com.jzrd.security.result.service.ICheckResultDetailService;
import com.jzrd.security.result.service.ICheckResultService;
import com.jzrd.security.result.service.IQueryCollectionService;
import com.jzrd.security.system.controller.BaseController;
import com.jzrd.security.system.entity.SecurityConstant;
import com.jzrd.security.system.utils.SecurityUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @description:
 * @author: Dreamer
 * @date: 2020-05-13 14:56
 */
@Slf4j
@Validated
@Controller("queryview")
public class ViewController extends BaseController {

    @Autowired
    private IQueryCollectionService queryCollectionService;

    @Autowired
    private ICheckAttachmentService attachmentService;

    @Autowired
    private ICheckResultService resultService;

    @ApiOperation(value = "根据检查结果ID获取结果明细和附件列表",
            notes = "弹框显示页")
    @GetMapping(SecurityConstant.VIEW_PREFIX + "query/detail/{resultId}")
    public String viewResultDetail(@PathVariable("resultId") int resultId, Model model) {
        // 根据筛选条件查询结果明细
        List<QueryCollection> detailList = queryCollectionService.getResultDetails(resultId);

        // 用于存放根据检查结果ID查询到的检查附件List
        List<CheckAttachment> attachmentList = attachmentService.list(new QueryWrapper<CheckAttachment>().eq("result_id", resultId));
        // 查询结果为空则不放入查询附件条件中

        model.addAttribute("resultId",resultId);
        model.addAttribute("details", detailList);
        model.addAttribute("attachments", attachmentList);
        model.addAttribute("remark",resultService.getById(resultId).getRemark());
        return SecurityUtil.view("views/des/queryDetail");
    }

}
