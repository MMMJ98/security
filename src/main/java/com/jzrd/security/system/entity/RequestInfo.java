package com.jzrd.security.system.entity;

import lombok.Data;

/**
 * @description:
 * @author: Dreamer
 * @date: 2020-05-16 20:59
 */
@Data
public class RequestInfo {
    // 状态码 1001 操作成功  2001 参数错误 2002 系统异常 3001
    private int code;
    // 返回信息
    private String msg;

    private User userInfo;

}
