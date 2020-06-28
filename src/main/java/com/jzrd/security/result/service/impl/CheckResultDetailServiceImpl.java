package com.jzrd.security.result.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jzrd.security.result.entity.CheckResultDetail;
import com.jzrd.security.result.mapper.CheckResultDetailMapper;
import com.jzrd.security.result.service.ICheckResultDetailService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @description:
 * @author: Dreamer
 * @date: 2020-04-23 23:11
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class CheckResultDetailServiceImpl extends ServiceImpl<CheckResultDetailMapper, CheckResultDetail> implements ICheckResultDetailService {
}
