package com.cont.controller;

import com.cont.pojo.Book;
import com.cont.pojo.User;
import com.cont.service.BookService;
import com.cont.vo.ErrorResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

//@CrossOrigin
@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookService bookService;
    @RequestMapping("/list")
    public ErrorResult getBookList(@RequestBody Map<String, String> param) {
        List<Book> bookList = this.bookService.getBookList();
        if (bookList.isEmpty()) {
            ErrorResult errorResult = ErrorResult.builder().code("401").msg("No User information").build();
            return errorResult;
        }
        return ErrorResult.builder().data(bookList).code("200").msg("success").build();

    }
    @RequestMapping("/add")
    public ErrorResult bookAdd(@RequestBody Map<String, String> param) {
        try {
           String bookName = param.get("bookName");
           String date = param.get("date");
           String type = param.get("type");
            boolean result = this.bookService.bookAdd(bookName, date, type);
            if (result == true) {
                return ErrorResult.builder().code("200").msg("success").build();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        ErrorResult errorResult = ErrorResult.builder().code("401").msg("add error").build();
        return errorResult;
    }
    @RequestMapping("/update")
    public ErrorResult bookUpdate(@RequestBody Map<String, String> param) {
        try {
            String bookName = param.get("bookName");
            String date = param.get("date");
            String type = param.get("type");
            boolean result = this.bookService.bookAdd(bookName, date, type);
            if (result == true) {
                return ErrorResult.builder().code("200").msg("success").build();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        ErrorResult errorResult = ErrorResult.builder().code("401").msg("update error").build();
        return errorResult;
    }
    @RequestMapping("/delete/{id}")
    public ErrorResult userDelete( @PathVariable Integer id) {

        try {

            boolean result = this.bookService.bookDelete(Integer.valueOf(id));

            if (result == true) {
                return ErrorResult.builder().code("200").msg("success").build();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        ErrorResult errorResult = ErrorResult.builder().code("401").msg("delete error").build();
        return errorResult;
    }
}
