package com.jzrd.security.core.controller;

import com.jzrd.security.core.entity.*;
import com.jzrd.security.core.service.*;
import com.jzrd.security.system.entity.SecurityResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.SecurityException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @description:
 * @author: Dreamer
 * @date: 2020-04-23 18:53
 */
@Slf4j
@RestController
@RequestMapping("checkInfo")
@Api(value = "检查信息管理类")
public class CheckInfoController {

    @Autowired
    private ICheckSheetService sheetService;

    @Autowired
    private ICheckEntryService entryService;

    @Autowired
    private ICheckItemService itemService;

    @Autowired
    private ICheckObjectService objectService;

    @Autowired
    private IObjectTypeService typeService;

    /**
     * 获取所有检查表信息（单表）
     *
     * @return
     */
    @GetMapping("allSheets")
    @ApiOperation(value = "获取所有检查表信息（单表）",notes = "查询过后返回所有检查表信息对象")
    public SecurityResponse getAllSheets() {
        List<CheckSheet> sheetList = sheetService.list();
        return new SecurityResponse().success().data(sheetList);
    }

    /**
     * 获取所有检查对象类型（单表）
     *
     * @return
     */
    @GetMapping("allTypes")
    @ApiOperation(value = "获取所有检查对象类型（单表）",notes = "查询过后返回所有检查对象类型")
    public SecurityResponse getAllObjectTypes() {
        List<ObjectType> typeList = typeService.list();
        return new SecurityResponse().success().data(typeList);
    }

    /**
     * 根据检查表ID获取该检查表下的所有检查条目
     *
     * @param sheetId
     * @return
     */
    @GetMapping("getEntriesBySID/{sheetId}")
    @ApiOperation(value = "根据检查表ID获取该检查表下的所有检查条目",notes = "查询过后返回该检查表下的所有检查条目")
    @ApiImplicitParam(paramType = "path",name="sheetId",value = "检查表ID",dataType = "int",required = true)
    public SecurityResponse getEntriesBySheetId(@PathVariable("sheetId") int sheetId) {
        // 获取检查表名称
        String sheetName = sheetService.getById(sheetId).getSheetName();
        List<CheckEntry> entryList = entryService.getEntriesBySheetId(sheetId);
        for (CheckEntry entry: entryList) {
            entry.setSheetName(sheetName);
        }
        return new SecurityResponse().success().data(entryList);
    }

    /**
     * 根据检查条目ID获取该检查条目下所有检查项
     *
     * @param entryId
     * @return
     */
    @GetMapping("getItemsByEID/{entryId}")
    @ApiOperation(value = "根据检查条目ID获取该检查条目下所有检查项",notes = "查询过后返回该检查条目下的所有检查项")
    @ApiImplicitParam(paramType = "path",name="entryId",value = "检查条目ID",dataType = "int",required = true)
    public SecurityResponse getItemsByEntryId(@PathVariable("entryId") int entryId) {
        List<CheckItem> itemList = itemService.getItemsByEntryId(entryId);
        for (CheckItem items:itemList) {
            if (items.getAttrType().equals("t")){
                items.setItemResult(String.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date())));
            }
            if (items.getAttrType().equals("b")||items.getAttrType().equals("e")){
                items.setItemResult(items.getExtInfo().split("\\|")[0]);
            }
            if (items.getAttrType().equals("n")){
                items.setItemResult(String.valueOf(0));
            }
            if (items.getAttrType().equals("d")){
                items.setItemResult(String.valueOf(0.0));
            }
        }
        return new SecurityResponse().success().data(itemList);
    }

    /**
     * 根据检查条目ID获取该条目下所有检查对象类型
     *
     * @param entryId
     * @return
     */
    @GetMapping("getTypesByEID/{entryId}")
    @ApiOperation(value = "根据检查条目ID获取该检查条目下所有检查对象类型",notes = "查询过后返回该检查条目下的检查对象类型")
    @ApiImplicitParam(paramType = "path",name="entryId",value = "检查条目ID",dataType = "int",required = true)
    public SecurityResponse getTypesByEntryId(@PathVariable("entryId") int entryId) {
        List<ObjectType> typeList = typeService.getTypesByEntryId(entryId);
        return new SecurityResponse().success().data(typeList);
    }

    /**
     * 根据对象类型ID获取该对象类型下所有检查对象
     * 结果集合中包含typeID，typeName,objectId,objectName
     * @param typeId
     * @return
     */
    @GetMapping("getObjectsByOTI/{typeId}")
    @ApiOperation(value = "根据对象类型ID获取该对象类型下所有检查对象",notes = "结果集合中包含typeID，typeName,objectId,objectName")
    @ApiImplicitParam(paramType = "path",name="typeId",value = "对象类型ID",dataType = "int",required = true)
    public SecurityResponse getObjectsByObjectTypeId(@PathVariable("typeId") int typeId) {
        List<CheckObject> objectList = objectService.getObjectsByObjectTypeId(typeId);
        return new SecurityResponse().success().data(objectList);
    }


    /**
     * 新增检查表信息（新增/修改）
     *
     * @return
     */
    @PostMapping("saveSheet")
    @ApiOperation(value = "保存一条检查表信息（新增/修改）",notes = "保存之后返回结果代码")
    @ApiImplicitParam(name = "sheet",value = "检查表信息",required = true)
    @ApiResponse(code = 400,message = "参数没有填好",response = String.class)
    public SecurityResponse addSheet(@RequestBody CheckSheet sheet){
        try {
            this.sheetService.saveOrUpdate(sheet);
            return new SecurityResponse().success();
        } catch (Exception e) {
            String message = "新增检查表失败";
            log.error(message, e);
            throw new SecurityException(message);
        }
    }

    /**
     * 根据检查表ID保存检查条目（新增/修改）
     *
     * @return
     */
    @PostMapping("saveEntry")
    @ApiOperation(value = "根据检查表ID保存检查条目（新增/修改）",notes = "保存之后返回检查条目对象")
    @ApiImplicitParam(name = "entry",value = "检查条目信息",required = true)
    @ApiResponse(code = 400,message = "参数没有填好",response = String.class)
    public SecurityResponse saveEntry(@RequestBody CheckEntry entry){
        try {
            this.entryService.saveOrUpdate(entry);
            return new SecurityResponse().success();
        } catch (Exception e) {
            String message = "保存检查条目失败";
            log.error(message, e);
            throw new SecurityException(message);
        }
    }

    /**
     * 根据检查条目保存检查项（新增/修改）
     *
     * @return
     */
    @PostMapping("saveItem")
    @ApiOperation(value = "根据检查条目保存检查项（新增/修改）",notes = "保存之后返回结果代码")
    @ApiImplicitParam(name = "item",value = "检查项信息",required = true)
    @ApiResponse(code = 400,message = "参数没有填好",response = String.class)
    public SecurityResponse saveItem(@RequestBody CheckItem item){
        try {
            this.itemService.saveOrUpdate(item);
            return new SecurityResponse().success();
        } catch (Exception e) {
            String message = "保存检查项失败";
            log.error(message, e);
            throw new SecurityException(message);
        }
    }

    /**
     * 保存对象类型（新增/修改）
     * @param type
     * @return
     */
    @PostMapping("saveType")
    @ApiOperation(value = "保存对象类型（新增/修改）",notes = "保存之后返回结果代码")
    @ApiImplicitParam(name = "type",value = "检查对象类型信息",required = true)
    @ApiResponse(code = 400,message = "参数没有填好",response = String.class)
    public SecurityResponse saveType(@RequestBody ObjectType type){
        try {
            this.typeService.saveOrUpdate(type);
            return new SecurityResponse().success();
        } catch (Exception e) {
            String message = "保存对象类型失败";
            log.error(message, e);
            throw new SecurityException(message);
        }
    }

    /**
     *  根据对象类型保存检查对象（新增/修改）
     * @param object
     * @return
     */
    @PostMapping("saveObject")
    @ApiOperation(value = "根据对象类型保存检查对象（新增/修改）",notes = "保存之后返回结果代码")
    @ApiImplicitParam(name = "object",value = "检查对象信息",required = true)
    @ApiResponse(code = 400,message = "参数没有填好",response = String.class)
    public SecurityResponse saveObject(@RequestBody CheckObject object){
        try {
            this.objectService.saveOrUpdate(object);
            return new SecurityResponse().success();
        } catch (Exception e) {
            String message = "保存对象失败";
            log.error(message, e);
            throw new SecurityException(message);
        }
    }

}
