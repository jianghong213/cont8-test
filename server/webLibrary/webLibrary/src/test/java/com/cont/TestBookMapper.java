package com.cont;

import com.cont.mapper.BookMapper;
import com.cont.pojo.Book;
import com.cont.pojo.User;
import com.cont.service.BookService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.ref.PhantomReference;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestBookMapper {
    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private BookService bookService;
    @Test
    public void testInsert(){
        Book book = new Book();
        book.setBookName("sadsadsa");
        book.setDate("2020");
        book.setType("novel");
        int result = this.bookMapper.insert(book);
        System.out.println("result =>" +result);
        System.out.println("id => "+book.getId());
    }
    @Test
    public void testGetList(){
        List<Book> userList = this.bookService.getBookList();
        for (Book book : userList) {
            System.out.println(book);
        }
    }

    @Test
    public void testBookInsert(){
        boolean b = this.bookService.bookAdd("xiaoshuo", "2021", "novel");
        System.out.println(b);
    }
    @Test
    public void testBookUpdate(){
        boolean b = this.bookService.bookUpdate("xiaoshuo", "2030", "novel");
        System.out.println(b);
    }
    @Test
    public void testDelete(){
        boolean xiaoshuo = this.bookService.bookDelete(3);
        System.out.println(xiaoshuo);
    }


}
