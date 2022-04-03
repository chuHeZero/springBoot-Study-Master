package com.example.manage.book.impl;

import com.alibaba.fastjson.JSON;
import com.example.manage.book.IBookBizService;
import com.example.manage.es.IHighLevelRestService;
import com.example.manage.input.Book.BookInput;
import com.example.manage.input.elasticsearch.SearchInput;
import com.example.manage.vo.Book.BookVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookBizServiceImpl implements IBookBizService {

    private final IHighLevelRestService highLevelRestService;

    @Override
    public Map<String, Object> bookVoList(SearchInput searchInput) {
        searchInput.setIndices("book");
        SearchResponse searchResponse = highLevelRestService.list(searchInput);
        SearchHits hits = searchResponse.getHits();
        // 获取总数
        Long total = hits.getTotalHits().value;

        // 遍历封装列表对象
        List<BookVO> bookVOList = new ArrayList<>();
        SearchHit[] searchHits = hits.getHits();
        for (SearchHit searchHit : searchHits) {
            bookVOList.add(JSON.parseObject(searchHit.getSourceAsString(), BookVO.class));
        }
        // 封装Map参数返回
        Map<String, Object> result = new HashMap<String, Object>(16);
        result.put("count", total);
        result.put("data", bookVOList);
        return result;
    }

    @Override
    public BookVO selectBookById(String id) {
        SearchInput searchInput = new SearchInput();
        searchInput.setIndices("book");
        GetResponse response = highLevelRestService.selectIndicesDataById(id, searchInput);
        return JSON.parseObject(response.getSourceAsString(), BookVO.class);
    }

    @Override
    public Boolean add(BookInput bookInput) {
        SearchInput searchInput = new SearchInput();
        searchInput.setIndices("book");
        highLevelRestService.add(bookInput);
        return true;
    }

    @Override
    public Boolean updateBookById(BookInput bookInput) {
        SearchInput searchInput = new SearchInput();
        searchInput.setIndices("book");
        highLevelRestService.updateIndicesDataById(bookInput, searchInput);
        return true;
    }

    @Override
    public Boolean deleteBookById(String id) {
        SearchInput searchInput = new SearchInput();
        searchInput.setIndices("book");
        highLevelRestService.deleteIndicesDataById(id, searchInput);
        return true;
    }
}
