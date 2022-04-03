package com.example.manage.common;

import org.elasticsearch.action.search.SearchResponse;

public interface ICommonBizService {

    /**
     * 获取es信息
     *
     * @return
     */
    SearchResponse getEsInfo();
}
