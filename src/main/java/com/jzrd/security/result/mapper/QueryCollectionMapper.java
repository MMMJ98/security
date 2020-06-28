package com.jzrd.security.result.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jzrd.security.result.entity.CheckResultDetail;
import com.jzrd.security.result.entity.QueryCollection;
import com.jzrd.security.system.entity.QueryRequest;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @description:
 * @author: Dreamer
 * @date: 2020-05-13 10:43
 */
public interface QueryCollectionMapper extends BaseMapper<QueryCollection> {

    /**
     * 根据筛选条件得到该检查表下的所有检查信息
     * @param queryCollection
     * @return
     */
    IPage<QueryCollection> getResultInfos(Page page,@Param("queryCollection") QueryCollection queryCollection);

    List<QueryCollection> getResultDetails(@Param("resultId") int resultId);

    List<QueryCollection> getWholeResults(@Param("resultId") int resultId);

}
