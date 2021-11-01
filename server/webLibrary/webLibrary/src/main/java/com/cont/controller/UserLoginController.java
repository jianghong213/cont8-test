package com.cont.controller;

import com.cont.service.UserService;
import com.cont.vo.ErrorResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@CrossOrigin
@RestController
public class UserLoginController {
    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    // @CrossOrigin
    public ErrorResult login(@RequestBody Map<String,String> param){
        try {
            String username = param.get("loginName");
            String password = param.get("password");
            System.out.println(username);
            System.out.println(password);
            boolean data = this.userService.login(username,password);
            if (data == true ){
                return ErrorResult.builder().code("200").msg("success").build() ;
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        ErrorResult errorResult = ErrorResult.builder().code("401").msg("The user name or password is incorrect").build();
        return errorResult;

    }
}
