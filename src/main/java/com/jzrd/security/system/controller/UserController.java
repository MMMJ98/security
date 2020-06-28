package com.jzrd.security.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.jzrd.security.result.service.IUserService;
import com.jzrd.security.system.entity.RequestInfo;
import com.jzrd.security.system.entity.SecurityResponse;
import com.jzrd.security.system.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @description:
 * @author: Dreamer
 * @date: 2020-05-28 21:24
 */
@Slf4j
@RestController
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping("user/password/update")
    public RequestInfo updatePassword(
            @RequestParam("oldPassword") String oldPassword,
            @RequestParam("newPassword") String newPassword,
            HttpSession session) throws SecurityException {
        RequestInfo requestInfo = new RequestInfo();
        try {
            User currentUser = (User) session.getAttribute("user");
            if (!StringUtils.equals(currentUser.getPassword(),oldPassword)){
                requestInfo.setCode(3001);
                requestInfo.setMsg("原密码不正确！");
            }
            else {
                userService.update(null,new UpdateWrapper<User>().eq("name",currentUser.getUserName()).set("password",newPassword));
                requestInfo.setCode(1001);
                requestInfo.setMsg("修改密码成功！");
            }
            return requestInfo;
        } catch (Exception e) {
            log.error(requestInfo.getMsg(), e);
            throw new SecurityException(requestInfo.getMsg());
        }
    }

    @PostMapping("user/register")
    public RequestInfo registerUser(@RequestParam String userName){
        RequestInfo requestInfo = new RequestInfo();
        try {
            String [] arr = userName.split("\\s+");
            for(String ss : arr){
                if (!(ss.equals("")||ss.length()==0)){
                    // 判断该用户名是否存在
                    User user = userService.getOne(new QueryWrapper<User>().eq("name",ss));
                    if (user!= null){
                        requestInfo.setCode(3001);
                        requestInfo.setMsg("注册失败！该用户名已存在");
                    }else {
                        user = new User();
                        user.setUserName(ss);
                        user.setPassword("123456");
                        userService.save(user);
                        requestInfo.setCode(2001);
                        requestInfo.setMsg("注册成功！");
                        requestInfo.setUserInfo(user);
                    }

                }
            }

        }catch (Exception e){
            log.error(requestInfo.getMsg(),e);
            throw new SecurityException(requestInfo.getMsg());
        }
        return requestInfo;
    }
}
