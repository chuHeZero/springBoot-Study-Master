package com.example.manage.entity.business;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.manage.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author xiaoHuoLong
 * @since 2022-02-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("bill_order")
public class BillOrder extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 账单id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 交费单姓名
     */
    private String personName;

    /**
     * 交费单证件号
     */
    private Integer idNumber;

    /**
     * 应交金额
     */
    private BigDecimal payAmt;

    /**
     * 创建人
     */
    private Integer createBy;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改人
     */
    private Integer updateBy;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 是否有效（0：有效；1：删除）
     */
    private Integer deleted;


}
