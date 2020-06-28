package com.jzrd.security.core.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jzrd.security.core.entity.CheckObject;
import com.jzrd.security.core.mapper.CheckObjectMapper;
import com.jzrd.security.core.service.ICheckObjectService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @description:
 * @author: Dreamer
 * @date: 2020-04-23 22:50
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class CheckObjectServiceImpl extends ServiceImpl<CheckObjectMapper, CheckObject> implements ICheckObjectService {
    @Override
    public List<CheckObject> getObjectsByObjectTypeId(@Param("typeId") int typeId) {
        return this.baseMapper.getObjectsByObjectTypeId(typeId);
    }
}
