package com.jzrd.security.core.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jzrd.security.core.entity.ObjectType;
import com.jzrd.security.core.mapper.ObjectTypeMapper;
import com.jzrd.security.core.service.IObjectTypeService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @description:
 * @author: Dreamer
 * @date: 2020-04-23 22:51
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class ObjectTypeServiceImpl extends ServiceImpl<ObjectTypeMapper, ObjectType> implements IObjectTypeService {
    @Override
    public List<ObjectType> getTypesByEntryId(@Param("entryId") int entryId) {
        return this.baseMapper.getTypesByEntryId(entryId);
    }
}
