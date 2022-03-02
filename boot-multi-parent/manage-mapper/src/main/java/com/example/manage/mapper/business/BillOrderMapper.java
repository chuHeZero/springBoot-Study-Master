package com.example.manage.mapper.business;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.manage.entity.business.BillOrder;
import com.example.manage.vo.business.BillOrderVO;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xiaoHuoLong
 * @since 2022-02-28
 */
public interface BillOrderMapper extends BaseMapper<BillOrder> {

    /**
     * @Description: 根据账单id获取账单详情
     * @Param: [billId]
     * @Return: com.example.manage.vo.business.BillOrderVO
     * @Author: XiaoHuoLong
     * @Date: 2022/2/28 16:37
     */
    BillOrderVO getBillOrderByID(@Param("billId") Integer billId);
}
