package com.cont;

import com.cont.mapper.UserMapper;
import com.cont.pojo.User;
import com.cont.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.jws.soap.SOAPBinding;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestUserMapper {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserService userService;

    @Test
    public void testInsert(){
        User user = new User();
        user.setUserName("han");
        user.setEmail("12723817@qq.com");
        user.setPassword("213131");
        user.setName("sdadsadsa");
        int result = this.userMapper.insert(user);
        System.out.println("result =>" +result);
        System.out.println("id => "+user.getId());
    }
    @Test
    public void TestUpdateById(){
        User user = new User();
        user.setId(1);
        user.setUserName("sdsadsadsa");
        user.setPassword("1231312321");
        int result = this.userMapper.updateById(user);
        System.out.println(result);
    }
    @Test
    public void TestDeleteById(){
        int result = this.userMapper.deleteById(2L);
        System.out.println("result =>" +result);
    }
    @Test
    public void TestGetList(){
        List<User> userList = this.userService.getUserList();
        for (User user : userList) {
            System.out.println(user);
        }

    }
    @Test
    public void TestUserAdd(){
        boolean b = this.userService.userAdd("han", "123", "han", "12312@sdsa");
        System.out.println(b);
    }
    @Test
    public void TestUserUpdate(){
        boolean b = this.userService.userUpdate("han", "23", "dsadsa", "dsadsa");
        System.out.println(b);
    }
    @Test
    public void TestUserDelete(){
        boolean wangwu = this.userService.userDelete(4);
        System.out.println(wangwu);
    }
}
