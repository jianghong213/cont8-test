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

public class enrollController {
    @Autowired
    private UserService userService;
    @RequestMapping("/enroll")
    public ErrorResult enroll(@RequestBody Map<String,String> param) {
        try {
            String username = param.get("loginName");
            String password = param.get("password");
            String email = param.get("email");
            String name = param.get("name");
            boolean result = this.userService.enroll(username, password, name, email);
            if (result == true) {
                return ErrorResult.builder().code("200").msg("success").build();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        ErrorResult errorResult = ErrorResult.builder().code("401").msg("The user name already exists, please re-enter it").build();
        return errorResult;
    }
}
