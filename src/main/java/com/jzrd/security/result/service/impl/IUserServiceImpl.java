package com.jzrd.security.result.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jzrd.security.result.mapper.UserMapper;
import com.jzrd.security.result.service.IUserService;
import com.jzrd.security.system.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @description:
 * @author: Dreamer
 * @date: 2020-05-16 20:04
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class IUserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
}
