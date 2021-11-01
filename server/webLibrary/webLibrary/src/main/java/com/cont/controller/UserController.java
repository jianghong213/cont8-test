package com.cont.controller;

import com.cont.pojo.User;
import com.cont.service.UserService;
import com.cont.vo.ErrorResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

//@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    @RequestMapping("/list")
    public ErrorResult getUserList(@RequestBody Map<String, String> param) {
        //selet *
        //list (book)
//        String username = param.get("loginName");
        List<User> userList = this.userService.getUserList();
        if (userList.isEmpty()) {
            ErrorResult errorResult = ErrorResult.builder().code("401").msg("No User information").build();
            return errorResult;
        }
        return ErrorResult.builder().data(userList).code("200").msg("success").build();

    }

    @RequestMapping("/add")
    public ErrorResult userAdd(@RequestBody Map<String, String> param) {
        try {
            String username = param.get("loginName");
            String password = param.get("password");
            String email = param.get("email");
            String name = param.get("name");
            boolean result = this.userService.userAdd(username, password, name, email);
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
    public ErrorResult userUpdate(@RequestBody Map<String, String> param) {
        try {
            String username = param.get("userName");
            String password = param.get("password");
            String email = param.get("email");
            String name = param.get("name");
            boolean result = this.userService.userUpdate(username, password, name, email);
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

            boolean result = this.userService.userDelete(Integer.valueOf(id));

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
