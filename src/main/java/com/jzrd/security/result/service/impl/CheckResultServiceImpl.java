package com.jzrd.security.result.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jzrd.security.result.entity.CheckResult;
import com.jzrd.security.result.mapper.CheckResultMapper;
import com.jzrd.security.result.service.ICheckResultService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @description:
 * @author: Dreamer
 * @date: 2020-04-23 18:58
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class CheckResultServiceImpl extends ServiceImpl<CheckResultMapper, CheckResult> implements ICheckResultService {


}
