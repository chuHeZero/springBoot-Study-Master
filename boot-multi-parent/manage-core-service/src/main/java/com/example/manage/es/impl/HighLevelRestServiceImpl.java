package com.example.manage.es.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.example.manage.constant.CommonConstant;
import com.example.manage.es.IHighLevelRestService;
import com.example.manage.exception.BizException;
import com.example.manage.input.Book.BookInput;
import com.example.manage.input.elasticsearch.SearchInput;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.DocWriteRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.stereotype.Repository;

import java.io.IOException;

import static com.example.manage.enums.es.ElasticsearchErrorCodeEnum.*;

@Slf4j
@RequiredArgsConstructor
@Repository
public class HighLevelRestServiceImpl implements IHighLevelRestService {

    private final RestHighLevelClient restHighLevelClient;

    @Override
    public SearchResponse getEsInfo() {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.source(searchSourceBuilder);
        // 查询ES
        try {
            SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            return searchResponse;
        } catch (IOException e) {
            log.error(e.toString());
            throw new BizException(GET_INFO_ERROR);
        }
    }

    @Override
    public SearchResponse list(SearchInput searchInput) {
        Integer page = ObjectUtil.isEmpty(searchInput.getPage()) ? 1 : searchInput.getPage();
        Integer rows = ObjectUtil.isEmpty(searchInput.getRows()) ? 10 : searchInput.getRows();
        String keyword = searchInput.getKeyword();

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        // 分页采用简单的from + size分页，适用数据量小的，了解更多分页方式可自行查阅资料
        searchSourceBuilder.from((page - 1) * rows);
        searchSourceBuilder.size(rows);
        // 查询条件，只有查询关键字不为空才带查询条件
        if (StrUtil.isNotBlank(keyword)) {
            QueryBuilder queryBuilder = QueryBuilders.multiMatchQuery(keyword, "name", "desc");
            searchSourceBuilder.query(queryBuilder);
        }

        SearchRequest searchRequest = new SearchRequest();
        searchRequest.source(searchSourceBuilder);
        // 指定检索的索引
        searchRequest.indices(searchInput.getIndices());
        try {
            // 查询ES
            return restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            log.error(e.toString());
            throw new BizException(LOAD_DATA_ERROR);
        }
    }

    @Override
    public GetResponse selectIndicesDataById(String id, SearchInput searchInput) {
        GetRequest request = new GetRequest(searchInput.getIndices(), id);
        try {
            //操作ES
            return restHighLevelClient.get(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            log.error(e.toString());
            throw new BizException(LOAD_DATA_ERROR);
        }
    }

    @Override
    public IndexResponse add(BookInput bookInput) {
        IndexRequest indexRequest = new IndexRequest(CommonConstant.INDEX);
        bookInput.setId(System.currentTimeMillis());
        String source = JSON.toJSONString(bookInput);
        indexRequest.id(bookInput.getId().toString()).source(source, XContentType.JSON);
        try {
            // 操作ES
            return restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            log.error(e.toString());
            throw new BizException(ADD_DATA_ERROR);
        }
    }

    @Override
    public UpdateResponse updateIndicesDataById(BookInput bookInput, SearchInput searchInput) {
        UpdateRequest updateRequest = new UpdateRequest(searchInput.getIndices(), bookInput.getId().toString());
        updateRequest.doc(JSON.toJSONString(bookInput), XContentType.JSON);
        try {
            // 操作ES
            return restHighLevelClient.update(updateRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            log.error(e.toString());
            throw new BizException(UPDATE_DATA_ERROR);
        }
    }

    @Override
    public DeleteResponse deleteIndicesDataById(String id, SearchInput searchInput) {
        DeleteRequest deleteRequest = new DeleteRequest(searchInput.getIndices(), id);
        try {
            return restHighLevelClient.delete(deleteRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            log.error(e.toString());
            throw new BizException(DELETE_DATA_ERROR);
        }
    }


}
