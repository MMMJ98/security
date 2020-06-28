package com.jzrd.security.core.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jzrd.security.core.entity.CheckEntry;
import com.jzrd.security.core.mapper.CheckEntryMapper;
import com.jzrd.security.core.service.ICheckEntryService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @description:
 * @author: Dreamer
 * @date: 2020-04-23 18:54
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class CheckEntryServiceImpl extends ServiceImpl<CheckEntryMapper, CheckEntry> implements ICheckEntryService {

    @Override
    public List<CheckEntry> getEntriesBySheetId(@Param("sheetId") int sheetId) {
        return this.baseMapper.getEntriesBySheetId(sheetId);
    }
}
