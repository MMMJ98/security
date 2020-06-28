package com.jzrd.security.system.controller;

import com.jzrd.security.core.service.ICheckSheetService;
import com.jzrd.security.system.entity.RedisInfo;
import com.jzrd.security.system.entity.SecurityConstant;
import com.jzrd.security.system.exception.RedisConnectException;
import com.jzrd.security.system.service.IRedisService;
import com.jzrd.security.system.utils.SecurityUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @description:
 * @author: Dreamer
 * @date: 2020-05-17 16:49
 */
@Slf4j
@Validated
@Controller
public class ViewController {
    @Autowired
    private ICheckSheetService sheetService;

    @Autowired
    private IRedisService redisService;

    @GetMapping("/login")
    public String login(Model model) {
        return SecurityUtil.view("views/system/login");
    }

    @GetMapping(SecurityConstant.VIEW_PREFIX + "query")
    public String queryCollection(Model model,HttpSession session) {
        model.addAttribute("user",session.getAttribute("user"));
        model.addAttribute("sheetNames", sheetService.list());
        return SecurityUtil.view("views/query");
    }

    @GetMapping(SecurityConstant.VIEW_PREFIX + "user/updatePwd")
    public String userPwdUpdate(Model model,HttpSession session) {
        model.addAttribute("user",session.getAttribute("user"));
        return SecurityUtil.view("views/system/user/passwordUpdate");
    }

    @GetMapping(SecurityConstant.VIEW_PREFIX + "user/register")
    public String userRegister(Model model,HttpSession session) {
        model.addAttribute("user",session.getAttribute("user"));
        return SecurityUtil.view("views/system/user/register");
    }

//    @GetMapping("redis/info")
//    @RequiresPermissions("redis:view")
//    public String getRedisInfo(Model model) throws RedisConnectException {
//        List<RedisInfo> infoList = this.redisService.getRedisInfo();
//        model.addAttribute("infoList", infoList);
//        return SecurityUtil.view("monitor/redisInfo");
//    }

//    @GetMapping("redis/terminal")
//    public String redisTerminal(Model model) {
//        String osName = System.getProperty("os.name");
//        model.addAttribute("osName", osName);
//        return SecurityUtil.view("monitor/redisTerminal");
//    }

}
