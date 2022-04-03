package com.example.manage.input.elasticsearch;

import lombok.Data;

@Data
public class SearchInput {
    //页码
    Integer page;

    // 条数
    Integer rows;

    // 关键字
    String keyword;

    // 指定索引库
    String indices;
}
