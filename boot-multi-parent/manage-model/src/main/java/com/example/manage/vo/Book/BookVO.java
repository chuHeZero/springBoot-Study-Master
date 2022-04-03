package com.example.manage.vo.Book;


import lombok.Data;

import java.io.Serializable;

/**
 * BookVo
 */
@Data
public class BookVO implements Serializable {

    /**
     * Id
     */
    private Long id;

    /**
     * 名称
     */
    private String name;

    /**
     * 描述
     */
    private String remark;

    /**
     * 空构造方法
     */
    public BookVO() {
    }

    /**
     * 构造赋值方法
     */
    public BookVO(Long id, String name, String remark) {
        this.id = id;
        this.name = name;
        this.remark = remark;
    }
}
