package com.example.manage.api.controller.admin.book;

import com.example.manage.api.controller.admin.common.BaseController;
import com.example.manage.book.IBookBizService;
import com.example.manage.common.ICommonBizService;
import com.example.manage.domain.ResultData;
import com.example.manage.input.Book.BookInput;
import com.example.manage.input.elasticsearch.SearchInput;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Api(value = "书籍查询", tags = "书籍查询")
@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController extends BaseController {

    private final ICommonBizService commonBizService;

    private final IBookBizService bookBizService;

    @ApiOperation(value = "获取es信息")
    @ApiOperationSupport(order = 1)
    @GetMapping("/getEsInfo")
    @ResponseBody
    public ResultData getEsInfo() {
        return success(commonBizService.getEsInfo());
    }

    @ApiOperation(value = "获取列表")
    @ApiOperationSupport(order = 2)
    @PostMapping("/list")
    @ResponseBody
    public ResultData list(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer rows, String keyword) {
        SearchInput searchInput = new SearchInput();
        searchInput.setPage(page);
        searchInput.setRows(rows);
        searchInput.setKeyword(keyword);
        return success(bookBizService.bookVoList(searchInput));
    }

    @ApiOperation(value = "根据id获取信息")
    @ApiOperationSupport(order = 3)
    @PostMapping("/selectBookById/{id}")
    @ResponseBody
    public ResultData selectBookById(@PathVariable("id") String id) {
        return success(bookBizService.selectBookById(id));
    }

    @ApiOperation(value = "新增")
    @ApiOperationSupport(order = 4)
    @PostMapping("/add")
    @ResponseBody
    public ResultData add(@RequestBody BookInput bookInput) {
        return success(bookBizService.add(bookInput));
    }

    @ApiOperation(value = "根据id修改")
    @ApiOperationSupport(order = 5)
    @PostMapping("/updateBookById")
    @ResponseBody
    public ResultData updateBookById(@RequestBody BookInput bookInput) {
        return success(bookBizService.updateBookById(bookInput));
    }

    @ApiOperation(value = "根据id删除")
    @ApiOperationSupport(order = 6)
    @PostMapping("/deleteBookById/{id}")
    @ResponseBody
    public ResultData deleteBookById(@PathVariable("id") String id) {
        return success(bookBizService.deleteBookById(id));
    }
}
