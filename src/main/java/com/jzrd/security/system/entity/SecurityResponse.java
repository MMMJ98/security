package com.jzrd.security.system.entity;

import org.springframework.http.HttpStatus;

import java.util.HashMap;

/**
 * @description:
 * @author: Dreamer
 * @date: 2020-04-24 15:02
 */
public class SecurityResponse extends HashMap<String, Object> {

    private static final long serialVersionUID = -8713837118340960775L;

    public SecurityResponse code(HttpStatus status) {
        this.put("code", status.value());
        return this;
    }

    public SecurityResponse message(String message) {
        this.put("message", message);
        return this;
    }

    public SecurityResponse data(Object data) {
        this.put("data", data);
        return this;
    }

    public SecurityResponse success() {
        this.code(HttpStatus.OK);
        return this;
    }

    public SecurityResponse fail() {
        this.code(HttpStatus.INTERNAL_SERVER_ERROR);
        return this;
    }

    @Override
    public SecurityResponse put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
