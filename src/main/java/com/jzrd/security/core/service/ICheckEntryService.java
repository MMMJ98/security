package com.jzrd.security.core.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jzrd.security.core.entity.CheckEntry;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @description:
 * @author: Dreamer
 * @date: 2020-04-23 18:54
 */
public interface ICheckEntryService extends IService<CheckEntry> {

    /**
     * 根据检查表ID得到该检查表的所有检查结果
     * @param sheetId
     * @return
     */
    List<CheckEntry> getEntriesBySheetId(@Param("sheetId") int sheetId);
}
