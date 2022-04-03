package com.example.manage.business.impl;

import com.example.manage.business.IBillOrderBizService;
import com.example.manage.business.IBillOrderService;
import com.example.manage.cache.IRedisService;
import com.example.manage.vo.business.BillOrderVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author: xiaoHuoLong
 * @Date: 2022/2/28 14:46
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class IBillOrderBizServiceImpl implements IBillOrderBizService {

    private final IBillOrderService billOrderService;

    private final IRedisService redisService;

    @Override
    public BillOrderVO getBillOrderById(Integer billId) {
        redisService.cacheValue("a","dddddddddddddddddddddddddd");
        System.out.println(redisService.getValue("a"));
        return billOrderService.getBillOrderById(billId);
    }
}
