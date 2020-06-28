package com.jzrd.security.core.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jzrd.security.core.entity.ObjectType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @description:
 * @author: Dreamer
 * @date: 2020-04-23 22:38
 */
public interface IObjectTypeService extends IService<ObjectType> {

    /**
     * 根据检查条目ID获取该条目下所有检查对象类型
     * @param entryId
     * @return
     */
    List<ObjectType> getTypesByEntryId(@Param("entryId") int entryId);
}
