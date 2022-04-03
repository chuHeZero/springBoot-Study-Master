package com.example.manage.input.Book;


import lombok.Data;

import java.io.Serializable;

/**
 * BookInput
 */
@Data
public class BookInput implements Serializable {

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
    public BookInput() {
    }

    /**
     * 构造赋值方法
     */
    public BookInput(Long id, String name, String desc) {
        this.id = id;
        this.name = name;
        this.remark = desc;
    }
}
