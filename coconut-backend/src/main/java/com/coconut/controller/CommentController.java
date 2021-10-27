package com.coconut.controller;

import com.coconut.common.Result;
import com.coconut.util.JwtUtil;
import com.coconut.model.dto.CommentDTO;
import com.coconut.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    CommentService commentService;

    @GetMapping(value = "/blog/{id}")
    public Result getCommentVOsByBlogId(@PathVariable("id") Integer blogId) {
        return Result.success(commentService.getCommentVObyBlogId(blogId));
    }

    @PostMapping(value = "/add")
    public Result add(@RequestHeader(value = "Authorization") String jwt, @Valid @RequestBody CommentDTO dto) {
        commentService.add(JwtUtil.getUserId(jwt), dto);
        return Result.success("");
    }

}
