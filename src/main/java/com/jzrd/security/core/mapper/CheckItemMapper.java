package com.jzrd.security.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jzrd.security.core.entity.CheckItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @description:
 * @author: Dreamer
 * @date: 2020-04-23 22:29
 */
public interface CheckItemMapper extends BaseMapper<CheckItem> {

    List<CheckItem> getItemsByEntryId(@Param("entryId") int entryId);
}
