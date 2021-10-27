package com.coconut.controller;

import com.coconut.common.Result;
import com.coconut.model.dto.LoginDTO;
import com.coconut.model.dto.RegisterDTO;
import com.coconut.model.entity.User;
import com.coconut.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping(value = "/register")
    public Result register(@Valid @RequestBody RegisterDTO dto) {
        User user = userService.register(dto);
        if(user == null) return Result.fail("Failed to sign up");
        return Result.success(user);
    }

    @PostMapping(value = "/login")
    public Result login(@Valid @RequestBody LoginDTO dto) {
        String jwt = userService.login(dto);
        if(jwt == null) return Result.fail("Failed to sign in");
        return Result.success(jwt);
    }

    @GetMapping(value = "/{id}")
    public Result getUserVO(@PathVariable("id") Integer userId) {
        return Result.success(userService.getUserVO(userId));
    }

}

