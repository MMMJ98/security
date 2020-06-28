package com.jzrd.security.result.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jzrd.security.result.entity.QueryCollection;
import com.jzrd.security.result.mapper.QueryCollectionMapper;
import com.jzrd.security.result.service.IQueryCollectionService;
import com.jzrd.security.system.entity.QueryRequest;
import com.jzrd.security.system.entity.SecurityConstant;
import com.jzrd.security.system.utils.SortUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @description:
 * @author: Dreamer
 * @date: 2020-05-13 10:53
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class QueryCollectionServiceImpl extends ServiceImpl<QueryCollectionMapper, QueryCollection> implements IQueryCollectionService {
    @Override
    public IPage<QueryCollection> getResultInfos(QueryRequest request, QueryCollection queryCollection) {
        Page<QueryCollection> page = new Page<>(request.getPage(), request.getLimit());
        return this.baseMapper.getResultInfos(page,queryCollection);
    }

    @Override
    public List<QueryCollection> getResultDetails(int resultId) {
        return this.baseMapper.getResultDetails(resultId);
    }

    @Override
    public List<QueryCollection> getWholeResults(int resultId) {
        return this.baseMapper.getWholeResults(resultId);
    }
}
