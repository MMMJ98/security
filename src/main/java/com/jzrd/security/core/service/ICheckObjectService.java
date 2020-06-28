package com.jzrd.security.core.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jzrd.security.core.entity.CheckObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @description:
 * @author: Dreamer
 * @date: 2020-04-23 22:37
 */
public interface ICheckObjectService extends IService<CheckObject> {

    /**
     * 根据对象类型ID得到该对象类型下所有检查对象
     * @param typeId
     * @return
     */
    List<CheckObject> getObjectsByObjectTypeId(@Param("typeId") int typeId);
}
