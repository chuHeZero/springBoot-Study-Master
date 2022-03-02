package com.example.manage.business;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.manage.entity.business.BillOrder;
import com.example.manage.vo.business.BillOrderVO;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author xiaoHuoLong
 * @since 2022-02-28
 */
public interface IBillOrderService extends IService<BillOrder> {

    /**
     * @Description: 根据账单id获取账单详情
     * @Param: [billId]
     * @Return: com.example.manage.vo.business.BillOrderVO
     * @Author: XiaoHuoLong
     * @Date: 2022/2/28 16:37
     */
    BillOrderVO getBillOrderByID(Integer billId);
}
