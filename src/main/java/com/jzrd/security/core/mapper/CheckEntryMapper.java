package com.jzrd.security.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jzrd.security.core.entity.CheckEntry;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @description:
 * @author: Dreamer
 * @date: 2020-04-23 18:51
 */
public interface CheckEntryMapper extends BaseMapper<CheckEntry> {

    /**
     * 根据检查表ID获得检查条目信息
     * @param sheetId
     * @return
     */
    List<CheckEntry> getEntriesBySheetId(@Param("sheetId") int sheetId);
}
