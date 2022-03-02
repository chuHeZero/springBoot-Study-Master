package com.example.manage.business.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.manage.mapper.business.BillOrderMapper;
import com.example.manage.business.IBillOrderService;
import com.example.manage.entity.business.BillOrder;
import com.example.manage.vo.business.BillOrderVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaoHuoLong
 * @since 2022-02-28
 */
@Service
@RequiredArgsConstructor
public class BillOrderServiceImpl extends ServiceImpl<BillOrderMapper, BillOrder> implements IBillOrderService {

    @Override
    public BillOrderVO getBillOrderByID(Integer billId) {
        return baseMapper.getBillOrderByID(billId);
    }
}
