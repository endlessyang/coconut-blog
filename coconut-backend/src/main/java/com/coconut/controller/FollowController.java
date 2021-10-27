package com.coconut.controller;

import com.coconut.common.Result;
import com.coconut.util.JwtUtil;
import com.coconut.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/follow")
public class FollowController {

    @Autowired
    FollowService followService;

    @GetMapping(value = "isFollow/{id}")
    public Result isFollow(@RequestHeader(value = "Authorization") String jwt, @PathVariable("id") Integer parentId) {
        return Result.success(followService.isFollow(parentId, JwtUtil.getUserId(jwt)));
    }

    @GetMapping(value = "/{id}")
    public Result XORFollow(@RequestHeader(value = "Authorization") String jwt, @PathVariable("id") Integer parentId) {
        Integer followerId = JwtUtil.getUserId(jwt);
        if(followerId.equals(parentId)) return Result.fail("Can't Follow Yourself");
        return Result.success(followService.XORFollow(parentId, followerId));
    }

}

