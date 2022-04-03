package com.example.manage.api.controller.book;

import com.example.manage.api.controller.common.BaseController;
import com.example.manage.book.IBookBizService;
import com.example.manage.common.ICommonBizService;
import com.example.manage.domain.ResultData;
import com.example.manage.input.Book.BookInput;
import com.example.manage.input.elasticsearch.SearchInput;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Api(value = "书籍查询", tags = "书籍查询")
@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController extends BaseController {

    private final ICommonBizService commonBizService;

    private final IBookBizService bookBizService;

    @RequestMapping("/getEsInfo")
    @ResponseBody
    public ResultData getEsInfo() {
        return success(commonBizService.getEsInfo());
    }

    @RequestMapping("/list")
    @ResponseBody
    public ResultData list(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer rows, String keyword) {
        SearchInput searchInput = new SearchInput();
        searchInput.setPage(page);
        searchInput.setRows(rows);
        searchInput.setKeyword(keyword);
        return success(bookBizService.bookVoList(searchInput));
    }

    @RequestMapping("/selectBookById/{id}")
    @ResponseBody
    public ResultData selectBookById(@PathVariable("id") String id) {
        return success(bookBizService.selectBookById(id));
    }

    @RequestMapping("/add")
    @ResponseBody
    public ResultData add(@RequestBody BookInput bookInput) {
        return success(bookBizService.add(bookInput));
    }

    @RequestMapping("/updateBookById")
    @ResponseBody
    public ResultData updateBookById(@RequestBody BookInput bookInput) {
        return success(bookBizService.updateBookById(bookInput));
    }

    @RequestMapping("/deleteBookById/{id}")
    @ResponseBody
    public ResultData deleteBookById(@PathVariable("id") String id) {
        return success(bookBizService.deleteBookById(id));
    }
}
