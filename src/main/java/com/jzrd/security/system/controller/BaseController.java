package com.jzrd.security.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;

import javax.security.auth.Subject;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Dreamer
 */
public class BaseController {

    protected Map<String, Object> getDataTable(IPage<?> pageInfo) {
        Map<String, Object> data = new HashMap<>();
        data.put("rows", pageInfo.getRecords());
        data.put("total", pageInfo.getTotal());
        return data;
    }

}
