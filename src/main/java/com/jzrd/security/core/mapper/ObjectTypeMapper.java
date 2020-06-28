package com.jzrd.security.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jzrd.security.core.entity.ObjectType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @description:
 * @author: Dreamer
 * @date: 2020-04-23 22:31
 */
public interface ObjectTypeMapper extends BaseMapper<ObjectType> {

    /**
     * 根据检查条目ID获取该条目下所有检查对象类型
     * @param entryId
     * @return
     */
    List<ObjectType> getTypesByEntryId(@Param("entryId") int entryId);
}
