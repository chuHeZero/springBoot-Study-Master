package com.example.manage.business;

import com.example.manage.vo.business.BillOrderVO;

/**
 * @Description:
 * @Author: XiaoHuoLong
 * @Date: 2022/2/28 14:42
 */
public interface IBillOrderBizService {

    /**
     * @Description: 根据账单id获取账单详情
     * @Param: [billId]
     * @Return: com.example.manage.vo.business.BillOrderVO
     * @Author: XiaoHuoLong
     * @Date: 2022/2/28 16:37
     */
    BillOrderVO getBillOrderById(Integer billId);
}
