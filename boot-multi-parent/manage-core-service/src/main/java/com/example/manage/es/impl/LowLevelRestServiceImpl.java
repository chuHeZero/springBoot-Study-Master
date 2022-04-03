package com.example.manage.es.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.manage.constant.CommonConstant;
import com.example.manage.es.IHighLevelRestService;
import com.example.manage.es.ILowLevelRestService;
import com.example.manage.exception.BizException;
import com.example.manage.input.Book.BookInput;
import com.example.manage.input.elasticsearch.SearchInput;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.RequestLine;
import org.apache.http.entity.ContentType;
import org.apache.http.nio.entity.NStringEntity;
import org.apache.http.util.EntityUtils;
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
import org.elasticsearch.client.*;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.common.xcontent.json.JsonXContent;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.regex.Pattern;

import static com.example.manage.enums.es.ElasticsearchErrorCodeEnum.*;

@Slf4j
@RequiredArgsConstructor
@Repository
public class LowLevelRestServiceImpl implements ILowLevelRestService {

    /**
     * PATTERN
     */
    private static Pattern PATTERN = Pattern.compile("\\s*|\t|\r|\n");


    private final RestClient restClient;

    @Override
    public Object getEsInfo() {
        Request request = new Request("GET", "/");
        // performRequest是同步的，将阻塞调用线程并在请求成功时返回Response，如果失败则抛出异常
        Response response = null;
        try {
            response = restClient.performRequest(request);
            // 获取请求行
            RequestLine requestLine = response.getRequestLine();
            // 获取host
            HttpHost host = response.getHost();
            // 获取状态码
            int statusCode = response.getStatusLine().getStatusCode();
            // 获取响应头
            Header[] headers = response.getHeaders();
            // 获取响应体
            String responseBody = EntityUtils.toString(response.getEntity());
            return JSON.parseObject(responseBody);
        } catch (IOException e) {
            throw new BizException(GET_INFO_ERROR);
        }
    }

    @Override
    public Object asynchronous() {
        Request request = new Request("GET", "/");
        restClient.performRequestAsync(request, new ResponseListener() {
            @Override
            public void onSuccess(Response response) {
                log.info("异步执行HTTP请求并成功");
            }

            @Override
            public void onFailure(Exception exception) {
                log.info("异步执行HTTP请求并失败");
            }
        });
        return null;
    }

    @Override
    public Object list(SearchInput searchInput) {
        Integer page = ObjectUtil.isEmpty(searchInput.getPage()) ? 1 : searchInput.getPage();
        Integer rows = ObjectUtil.isEmpty(searchInput.getRows()) ? 10 : searchInput.getRows();
        String keyword = searchInput.getKeyword();

        Request request = new Request("POST", new StringBuilder("/_search").toString());
        // 添加Json返回优化
        request.addParameter("pretty", "true");
        // 拼接查询Json
        IndexRequest indexRequest = new IndexRequest();
        XContentBuilder builder = null;
        Response response = null;
        String responseBody = null;
        try {
            builder = JsonXContent.contentBuilder()
                    .startObject()
                    .startObject("query")
                    .startObject("multi_match")
                    .field("query", keyword)
                    .array("fields", new String[]{"name", "desc"})
                    .endObject()
                    .endObject()
                    .startObject("sort")
                    .startObject("id")
                    .field("order", "desc")
                    .endObject()
                    .endObject()
                    .endObject();
            indexRequest.source(builder);
            // 设置请求体并指定ContentType，如果不指定会乱码
            request.setEntity(new NStringEntity(indexRequest.source().utf8ToString(), ContentType.APPLICATION_JSON));
            // 执行HTTP请求
            response = restClient.performRequest(request);
            responseBody = EntityUtils.toString(response.getEntity());
        } catch (IOException e) {
            return null;
        }
        return JSON.parseObject(responseBody);
    }

    @Override
    public Object selectIndicesDataById(String id, SearchInput searchInput) {
        Request request = new Request("GET", new StringBuilder("/book/book/")
                .append(id).toString());
        // 添加Json返回优化
        request.addParameter("pretty", "true");
        Response response = null;
        String responseBody = null;
        try {
            // 执行HTTP请求
            response = restClient.performRequest(request);
            responseBody = EntityUtils.toString(response.getEntity());
        } catch (IOException e) {
            return null;
        }
        return JSON.parseObject(responseBody);
    }

    @Override
    public Object add(BookInput bookInput) {
        // Endpoint直接指定为Index/Type的形式
        /*Request request = new Request("POST", new StringBuilder("/book/book/").toString());*/
        // 防重复新增数据
        bookInput.setId(System.currentTimeMillis());
        Request request = new Request("PUT", new StringBuilder("/book/book/")
                .append(bookInput.getId()).append("/_create").toString());
        // 设置其他一些参数比如美化Json
        request.addParameter("pretty", "true");
        // 设置请求体并指定ContentType，如果不指定会乱码
        request.setEntity(new NStringEntity(JSONObject.toJSONString(bookInput), ContentType.APPLICATION_JSON));
        // 发送HTTP请求
        Response response = null;
        try {
            response = restClient.performRequest(request);
            // 获取响应体
            String responseBody = EntityUtils.toString(response.getEntity());
            return JSON.parseObject(responseBody);
        } catch (IOException e) {
            log.error(e.toString());
            throw new BizException(ADD_DATA_ERROR);
        }
    }

    @Override
    public Object updateIndicesDataById(BookInput bookInput, SearchInput searchInput) {
        // 构造HTTP请求
        /*Request request = new Request("POST", new StringBuilder("/book/book/")
                .append(bookDto.getId()).append("/_update").toString());*/
        Request request = new Request("PUT", new StringBuilder("/book/book/")
                .append(bookInput.getId()).toString());
        // 设置其他一些参数比如美化Json
        request.addParameter("pretty", "true");
        /*// 将数据丢进去，这里一定要外包一层'doc'，否则内部不能识别
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("doc", new JSONObject(bookDto));*/
        // 设置请求体并指定ContentType，如果不指定会乱码
        request.setEntity(new NStringEntity(JSONObject.toJSONString(bookInput), ContentType.APPLICATION_JSON));
        // 执行HTTP请求
        Response response = null;
        try {
            response = restClient.performRequest(request);
            // 获取返回的内容
            String responseBody = EntityUtils.toString(response.getEntity());
            return JSON.parseObject(responseBody);
        } catch (IOException e) {
            throw new BizException(UPDATE_DATA_ERROR);
        }

    }

    @Override
    public Object updateIndicesDataById2(BookInput bookInput, SearchInput searchInput) {
        // 构造HTTP请求
        Request request = new Request("POST", new StringBuilder("/book/book/")
                .append(bookInput.getId()).append("/_update").toString());
        // 设置其他一些参数比如美化Json
        request.addParameter("pretty", "true");
        JSONObject jsonObject = new JSONObject();
        // 创建脚本语言，如果是字符变量，必须加单引号
        StringBuilder op1 = new StringBuilder("ctx._source.name=").append("'" + bookInput.getName() + "'");
        jsonObject.put("script", op1);
        request.setEntity(new NStringEntity(jsonObject.toString(), ContentType.APPLICATION_JSON));
        // 执行HTTP请求
        Response response = null;
        try {
            response = restClient.performRequest(request);
            // 获取返回的内容
            String responseBody = EntityUtils.toString(response.getEntity());
            return JSON.parseObject(responseBody);
        } catch (IOException e) {
            throw new BizException(UPDATE_DATA_ERROR);
        }
    }

    @Override
    public Object deleteIndicesDataById(String id, SearchInput searchInput) {
        Request request = new Request("DELETE", new StringBuilder("/book/book/")
                .append(id).toString());
        request.addParameter("pretty", "true");
        // 执行HTTP请求
        Response response = null;
        try {
            response = restClient.performRequest(request);
            // 获取结果
            String responseBody = EntityUtils.toString(response.getEntity());
            return JSON.parseObject(responseBody);
        } catch (IOException e) {
            throw new BizException(DELETE_DATA_ERROR);
        }
    }

}
