package com.jzrd.security.result.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jzrd.security.result.entity.CheckAttachment;
import com.jzrd.security.result.mapper.CheckAttachmentMapper;
import com.jzrd.security.result.service.ICheckAttachmentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @description:
 * @author: Dreamer
 * @date: 2020-04-23 23:12
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class CheckAttachmentServiceImpl extends ServiceImpl<CheckAttachmentMapper, CheckAttachment> implements ICheckAttachmentService {
}
