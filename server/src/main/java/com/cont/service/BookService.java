package com.cont.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cont.mapper.BookMapper;
import com.cont.pojo.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookMapper bookMapper;

    public List<Book> getBookList() {
        List<Book> books = this.bookMapper.selectList(null);
        if (books.isEmpty()) {
            return null;
        }
        return books;
    }
    public boolean bookAdd(String bookName,String date,String type) {
       Book book = new Book();
       book.setBookName(bookName);
       book.setDate(date);
       book.setType(type);
        int insert = this.bookMapper.insert(book);
        if (insert == 1) {
            return true;
        }
        return false;
    }
    public boolean bookUpdate(String bookName,String date,String type) {
        Book book = new Book();
        book.setBookName(bookName);
        book.setDate(date);
        book.setType(type);
        QueryWrapper<Book> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("book_name", bookName);

        int update = this.bookMapper.update(book, queryWrapper);

        if (update == 1) {
            return true;
        }
        return false;
    }
    public boolean bookDelete(Integer id) {
        QueryWrapper<Book> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        int delete = this.bookMapper.delete(queryWrapper);
        if (delete == 1) {
            return true;
        }
        return false;
    }
}
