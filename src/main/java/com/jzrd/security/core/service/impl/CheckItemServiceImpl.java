package com.jzrd.security.core.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jzrd.security.core.entity.CheckItem;
import com.jzrd.security.core.mapper.CheckItemMapper;
import com.jzrd.security.core.service.ICheckItemService;
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
public class CheckItemServiceImpl extends ServiceImpl<CheckItemMapper, CheckItem> implements ICheckItemService {
    @Override
    public List<CheckItem> getItemsByEntryId(@Param("entryId") int entryId) {
        return this.baseMapper.getItemsByEntryId(entryId);
    }
}
