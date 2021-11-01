package com.cont;

import com.cont.mapper.BookMapper;
import com.cont.mapper.UserMapper;
import com.cont.service.BookService;
import com.cont.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestEnroll {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserService userService;

@Test
    public void TestEnroll(){
        boolean enroll = this.userService.enroll("sadsadsadsadsad", "213131", "sadsa", "2131231");
        System.out.println(enroll);
    }
}
