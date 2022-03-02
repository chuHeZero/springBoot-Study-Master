package com.example.manage.vo.business;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @Description:
 * @Author: xiaoHuoLong
 * @Date: 2022/2/28 14:25
 */
@Data
public class BillOrderVO implements Serializable {
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
