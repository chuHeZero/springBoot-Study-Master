package com.example.manage.api.controller.admin.business;

import com.example.manage.api.controller.admin.common.BaseController;
import com.example.manage.business.IBillOrderBizService;
import com.example.manage.vo.business.BillOrderVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "账单查询", tags = "账单查询")
@RestController
@RequestMapping("/billOrder")
@RequiredArgsConstructor
public class BillOrderController extends BaseController {

    private final IBillOrderBizService billOrderBizService;

    @ApiOperation(value = "测试接口")
    @PostMapping("/test")
    public String test() {
        return "hello World!!!";
    }

    @ApiOperation(value = "新增标签test2")
    @PostMapping("/getBillOrderById")
    @ResponseBody
    public BillOrderVO getBillOrderById() {
        return billOrderBizService.getBillOrderById(1);
    }
}
