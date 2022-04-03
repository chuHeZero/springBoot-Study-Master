package com.example.manage.book.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.manage.book.IBookService;
import com.example.manage.entity.book.Book;
import com.example.manage.mapper.book.BookMapper;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zzm
 * @since 2022-04-03
 */
@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements IBookService {

}
