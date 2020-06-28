package com.jzrd.security.core.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jzrd.security.core.entity.CheckSheet;
import com.jzrd.security.core.mapper.CheckSheetMapper;
import com.jzrd.security.core.service.ICheckSheetService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @description:
 * @author: Dreamer
 * @date: 2020-04-23 22:51
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class CheckSheetServiceImpl extends ServiceImpl<CheckSheetMapper, CheckSheet> implements ICheckSheetService {


}
