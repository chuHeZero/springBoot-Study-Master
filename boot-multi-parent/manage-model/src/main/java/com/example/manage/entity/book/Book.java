package com.example.manage.entity.book;

import com.example.manage.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author zzm
 * @since 2022-04-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("book")
public class Book extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Integer id;

    /**
     * 书名
     */
    private String name;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建人id
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
     * 是否删除（0：未删除；1：已删除）
     */
    private Integer deleted;


}
