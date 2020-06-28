package com.jzrd.security.system.exception;

/**
 * Security 系统内部异常
 *
 * @description:
 * @author: Dreamer
 * @date: 2020-04-24 15:03
 */
public class SecurityException extends Exception {

    private static final long serialVersionUID = -994962710559017255L;

    public SecurityException(String message) {
        super(message);
    }
}
