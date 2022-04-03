package com.example.manage.es;

import com.example.manage.input.Book.BookInput;
import com.example.manage.input.elasticsearch.SearchInput;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateResponse;

public interface ILowLevelRestService {

    /**
     * 获取es信息
     *
     * @return
     */
    Object getEsInfo();

    Object asynchronous();

    Object list(SearchInput searchInput);

    Object selectIndicesDataById(String id, SearchInput searchInput);

    Object add(BookInput bookInput);

    Object updateIndicesDataById(BookInput bookInput, SearchInput searchInput);

    Object updateIndicesDataById2(BookInput bookInput, SearchInput searchInput);

    Object deleteIndicesDataById(String id, SearchInput searchInput);
}
