package com.jzrd.security.result.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jzrd.security.result.entity.*;
import com.jzrd.security.result.mapper.CheckResultMapper;
import com.jzrd.security.result.service.IQueryCollectionService;
import com.jzrd.security.system.entity.SecurityResponse;
import com.jzrd.security.result.service.ICheckAttachmentService;
import com.jzrd.security.result.service.ICheckResultDetailService;
import com.jzrd.security.result.service.ICheckResultService;
import com.jzrd.security.system.controller.BaseController;
import com.jzrd.security.system.entity.QueryRequest;
import com.jzrd.security.system.utils.FileUtil;
import com.wuwenze.poi.ExcelKit;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @description:
 * @author: Dreamer
 * @date: 2020-04-23 18:57
 */
@Slf4j
@RestController
@RequestMapping("resultRecord")
@Api(value = "检查结果管理类")
public class ResultRecordController extends BaseController {

    @Autowired
    private ICheckResultService resultService;

    @Autowired
    private CheckResultMapper resultMapper;

    @Autowired
    private ICheckResultDetailService detailService;

    @Autowired
    private ICheckAttachmentService attachmentService;

    @Autowired
    private IQueryCollectionService queryCollectionService;

    // 附件上传地址
    @Value("${file.upload.path}")
    String filePath;

    /**
     * 根据筛选条件得到该检查表下的所有检查信息
     * (检查表名+检查条目+检查项+对象类型+检查对象)
     * @param queryCollection
     * @return
     */
    @RequestMapping(value = "getResultInfos",method = RequestMethod.GET)
    @ApiOperation(value = "根据筛选条件得到该检查表下的所有检查信息",
            notes = "返回结果包含检查表名+检查条目+检查项+对象类型+检查对象")
    @ApiImplicitParam(name="queryCollection",value = "筛选条件")
    public SecurityResponse getResultInfos(QueryCollection queryCollection,QueryRequest request){
        IPage<QueryCollection> queryPage = queryCollectionService.getResultInfos(request,queryCollection);
        Map<String, Object> dataTable = getDataTable(queryPage);
        return new SecurityResponse().success().data(dataTable);
    }

    /**
     * 保存检查结果（新增/修改）
     * @param result
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED,  rollbackFor = Exception.class)
    @PostMapping("addResult")
    @ApiOperation(value = "保存检查结果（新增/修改）",notes = "保存之后返回结果代码")
    @ApiImplicitParam(name = "sec",value = "检查结果信息",required = true)
    @ApiResponse(code = 400,message = "参数没有填好",response = String.class)
    public SecurityResponse addResult(@RequestBody CheckResult result){
        try {
            result.setSubmitTime(new Date());
            this.resultService.saveOrUpdate(result);
            return new SecurityResponse().success();
        } catch (Exception e) {
            String message = "保存结果记录失败";
            log.error(message, e);
            throw new SecurityException(message);
        }
    }

    /**
     * 保存结果明细（新增/修改）
     * @param detail
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @PostMapping("addResultDetail")
    @ApiOperation(value = "保存结果明细（新增/修改）",notes = "保存之后返回结果代码")
    @ApiImplicitParam(name = "detail",value = "检查结果明细信息",required = true)
    @ApiResponse(code = 400,message = "参数没有填好",response = String.class)
    public SecurityResponse addResultDetail(@RequestBody CheckResultDetail detail){
        try {
            this.detailService.saveOrUpdate(detail);
            return new SecurityResponse().success();
        } catch (Exception e) {
            String message = "保存结果明细失败";
            log.error(message, e);
            throw new SecurityException(message);
        }
    }

    /**
     * 上传附件(新增/修改)
     * @param file
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @PostMapping(value = "uploadAttachment")
    @ApiOperation(value = "上传附件(新增/修改)",notes = "保存之后返回结果代码")
    // swagger 2.9版本后文件上传的dataType调整为__file
    @ApiImplicitParam(name = "filePath",value = "待选择的附件",dataType = "__file", paramType = "form", required = true)
    @ApiResponse(code = 400,message = "参数没有填好",response = String.class)
    public SecurityResponse uploadAttachment(@RequestParam("filePath") MultipartFile file){
        // 获取上传文件名(此时的文件名为微信服务器的文件名)
        String filename = file.getOriginalFilename();
        Date date = new Date();
        // 定义上传文件保存路径（本地服务器的路径）
        String path = filePath +new SimpleDateFormat("yyyyMM").format(date);

        // 新建文件
        File newfilepath = new File(path, filename);
        // 判断路径是否存在，如果不存在就创建一个
        if (!newfilepath.getParentFile().exists()) {
            newfilepath.getParentFile().mkdirs();
        }
        try {
            // 写入文件
            file.transferTo(new File(newfilepath.getAbsolutePath()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new SecurityResponse().success().data(newfilepath.getPath().replaceAll("\\\\","/"));
    }

    /**
     *  根据检查条目保存检查结果（新增/修改）
     * @param collection
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    @PostMapping("saveResult")
    @ApiOperation(value = "根据检查条目保存检查结果（新增/修改）",notes = "保存之后返回结果代码")
    @ApiImplicitParam(name = "collection",value = "检查结果信息",required = true)
    @ApiResponse(code = 400,message = "参数没有填好",response = String.class)
    public SecurityResponse saveResult(@RequestBody CheckInfoCollection collection){

        // 用于存储某检查项下的结果信息
        CheckResult result = new CheckResult();
        result.setEntryId(collection.getEntryId());
        result.setObjectName(collection.getObjectName());
        result.setCheckPerson(collection.getCheckPerson());
        result.setSubmitTime(new Date());
        result.setRemark(collection.getRemark());

        // 根据检查条目ID保存检查结果
        int i = resultMapper.insert(result);

        int resultId = result.getResultId();

        for (DetailsList list : collection.getDetails()){
            //用于存储某检查结果的结果详细
            CheckResultDetail detail = new CheckResultDetail();
            detail.setResultId(resultId);
            detail.setItemId(list.getItemId());
            detail.setItemResult(list.getItemResult());

            // 根据检查结果ID保存检查结果详细
            detailService.saveOrUpdate(detail);
        }

        for (String as : collection.getAttachments()){
            // 用于存储某检查结果下的附件信息
            CheckAttachment attachment = new CheckAttachment();
            attachment.setResultId(resultId);
            attachment.setUrl(as);

            // 根据检查结果ID保存附件
            attachmentService.saveOrUpdate(attachment);
        }

        return new SecurityResponse().success();
    }

    /**
     * 文件下载（失败了会返回一个有部分数据的Excel）
     * <p>
     * 1. 创建excel对应的实体对象
     * <p>
     * 2. 设置返回的 参数
     * <p>
     * 3. 直接写，这里注意，finish的时候会自动关闭OutputStream,当然你外面再关闭流问题不大
     */
    @RequestMapping(value = "excel/getWholeResults/{resultId}",method = RequestMethod.GET)
    public void getWholeResults(@PathVariable("resultId") int resultId, HttpServletResponse response)
            throws SecurityException, IOException {
        List<QueryCollection> wholeResults = queryCollectionService.getWholeResults(resultId);
        try {
            // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
            String fileName = URLEncoder.encode("检查结果信息表", "UTF-8");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
            EasyExcel.write(response.getOutputStream(), QueryCollection.class)
                    .autoCloseStream(Boolean.FALSE).sheet("sheet1").doWrite(wholeResults);
        }catch (Exception e){
            // 重置response
            response.reset();
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            Map<String, String> map = new HashMap<String, String>();
            map.put("status", "failure");
            map.put("message", "下载文件失败" + e.getMessage());
            response.getWriter().println(JSON.toJSONString(map));
        }
    }
}
