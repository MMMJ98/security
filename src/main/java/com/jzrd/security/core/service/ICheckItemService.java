package com.jzrd.security.core.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jzrd.security.core.entity.CheckItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @description:
 * @author: Dreamer
 * @date: 2020-04-23 22:37
 */
public interface ICheckItemService extends IService<CheckItem> {

    List<CheckItem> getItemsByEntryId(@Param("entryId") int entryId);
}
