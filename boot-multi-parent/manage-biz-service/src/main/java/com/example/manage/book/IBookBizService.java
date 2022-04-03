package com.example.manage.book;

import com.example.manage.input.Book.BookInput;
import com.example.manage.input.elasticsearch.SearchInput;
import com.example.manage.vo.Book.BookVO;

import java.util.Map;

public interface IBookBizService {

    Map<String, Object> bookVoList(SearchInput searchInput);

    BookVO selectBookById(String id);

    Boolean add(BookInput bookInput);

    Boolean updateBookById(BookInput bookInput);

    Boolean deleteBookById(String id);

}
