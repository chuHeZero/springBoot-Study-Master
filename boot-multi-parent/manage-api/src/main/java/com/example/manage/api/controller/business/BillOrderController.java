package com.example.manage.api.controller.business;

import com.example.manage.api.controller.common.BaseController;
import com.example.manage.business.IBillOrderBizService;
import com.example.manage.vo.business.BillOrderVO;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "账单查询", tags = "账单查询")
@RestController
@RequestMapping("/billOrder")
@RequiredArgsConstructor
public class BillOrderController extends BaseController {

    private final IBillOrderBizService billOrderBizService;

    @RequestMapping("/test")
    public String test() {
        return "hello World!!!";
    }

    @RequestMapping("/test2")
    @ResponseBody
    public BillOrderVO test2() {
        return billOrderBizService.getBillOrderByID(2);
    }
}
