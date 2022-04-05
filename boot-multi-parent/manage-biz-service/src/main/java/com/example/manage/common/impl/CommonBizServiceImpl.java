package com.example.manage.common.impl;

import com.example.manage.cache.IRedisService;
import com.example.manage.common.ICommonBizService;
import com.example.manage.es.IHighLevelRestService;
import com.example.manage.system.ISysUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchResponse;
import org.springframework.stereotype.Repository;

@Slf4j
@RequiredArgsConstructor
@Repository
public class CommonBizServiceImpl implements ICommonBizService {

    private final IHighLevelRestService highLevelRestService;

    private final IRedisService redisService;

    @Override
    public SearchResponse getEsInfo() {
        return highLevelRestService.getEsInfo();
    }

    @Override
    public void cacheSet(String k, String v, long time) {
        redisService.cacheSet(k, v, time);
    }

}
