package com.example.manage.common;

import org.elasticsearch.action.search.SearchResponse;

public interface ICommonBizService {

    /**
     * 获取es信息
     *
     * @return org.elasticsearch.action.search.SearchResponse
     * @author zzm
     * @date 2022/4/3 21:15
     */
    SearchResponse getEsInfo();

    /**
     * 缓存登录信息
     *
     * @param k
     * @param v
     * @param time
     * @author zzm
     * @date 2022/4/4 21:14
     */
    void cacheSet(String k, String v, long time);
}
