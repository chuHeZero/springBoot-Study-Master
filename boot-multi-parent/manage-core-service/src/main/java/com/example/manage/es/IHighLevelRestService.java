package com.example.manage.es;

import com.example.manage.input.Book.BookInput;
import com.example.manage.input.elasticsearch.SearchInput;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateResponse;

/**
 * @author zzm
 * @date 2022/4/3 17:43
 */
public interface IHighLevelRestService {

    /**
     * 获取es信息
     *
     * @return org.elasticsearch.action.search.SearchResponse
     * @author zzm
     * @date 2022/4/3 17:43
     */
    SearchResponse getEsInfo();

    /**
     * 获取指定索引数据集
     *
     * @param searchInput
     * @return org.elasticsearch.action.search.SearchResponse
     * @author zzm
     * @date 2022/4/3 17:44
     */
    SearchResponse list(SearchInput searchInput);

    /**
     * 根据id检索指定索引数据
     *
     * @param id
     * @param searchInput
     * @return org.elasticsearch.action.get.GetResponse
     * @author zzm
     * @date 2022/4/3 17:44
     */
    GetResponse selectIndicesDataById(String id, SearchInput searchInput);

    /**
     * 往指定索引中添加数据
     *
     * @param bookInput
     * @return org.elasticsearch.action.index.IndexResponse
     * @author zzm
     * @date 2022/4/3 17:44
     */
    IndexResponse add(BookInput bookInput);

    /**
     * 根据id修改指定索引中的数据
     *
     * @param bookInput
     * @param searchInput
     * @return org.elasticsearch.action.update.UpdateResponse
     * @author zzm
     * @date 2022/4/3 17:44
     */
    UpdateResponse updateIndicesDataById(BookInput bookInput, SearchInput searchInput);

    /**
     * 根据id删除指定索引中的数据
     *
     * @param id
     * @param searchInput
     * @return org.elasticsearch.action.delete.DeleteResponse
     * @author zzm
     * @date 2022/4/3 17:45
     */
    DeleteResponse deleteIndicesDataById(String id, SearchInput searchInput);
}
