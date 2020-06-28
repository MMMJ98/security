package com.jzrd.security.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jzrd.security.result.service.IUserService;
import com.jzrd.security.system.entity.RequestInfo;
import com.jzrd.security.system.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @description:
 * @author: Dreamer
 * @date: 2020-05-16 20:07
 */
@Slf4j
@RestController
public class LoginController {

    @Autowired
    private IUserService userService;

    @PostMapping("login")
    public RequestInfo login(@RequestParam("username") String username,
                             @RequestParam("password") String password,
                             HttpSession session) {
        // 用于存储登录返回信息
        RequestInfo info = new RequestInfo();
        try {
            if (username != null && username.length() != 0) {
                if (password != null && password.length() != 0) {
                    User user = userService.getOne(new QueryWrapper<User>().eq("name", username));
                    if (user != null) {
                        if (!user.getPassword().equals(password)) {
                            info.setCode(2001);
                            info.setMsg("登录失败！用户名或密码错误！");
                        } else {
                            info.setCode(1001);
                            info.setMsg("登录成功！当前登录用户为" + username);
                            info.setUserInfo(user);
                            session.setAttribute("user",user);
                        }
                    } else {
                        info.setCode(2001);
                        info.setMsg("登录失败！该用户名不存在");
                    }
                } else {
                    info.setCode(2002);
                    info.setMsg("密码不能为空！");
                }
            } else {
                info.setCode(2002);
                info.setMsg("用户名不能为空！");
            }
            return info;
        } catch (Exception e) {
            log.error(info.getMsg(), e);
            throw new SecurityException(info.getMsg());
        }
    }
}
