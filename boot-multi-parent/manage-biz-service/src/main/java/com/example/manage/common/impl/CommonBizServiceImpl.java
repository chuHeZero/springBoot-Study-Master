package com.example.manage.common.impl;

import com.example.manage.common.ICommonBizService;
import com.example.manage.es.IHighLevelRestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchResponse;
import org.springframework.stereotype.Repository;

@Slf4j
@RequiredArgsConstructor
@Repository
public class CommonBizServiceImpl implements ICommonBizService {

    private final IHighLevelRestService highLevelRestService;

    @Override
    public SearchResponse getEsInfo() {
        return highLevelRestService.getEsInfo();
    }
}
