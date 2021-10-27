package com.coconut.controller;

import com.coconut.common.Result;
import com.coconut.model.dto.BlogDTO;
import com.coconut.model.entity.Blog;
import com.coconut.service.BlogService;
import com.coconut.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/blog")
public class BlogController {

    @Autowired
    BlogService blogService;

    @PostMapping("/edit")
    public Result edit(@RequestHeader(value = "Authorization") String jwt, @Valid @RequestBody BlogDTO dto) {
        Blog blog = blogService.edit(JwtUtil.getUserId(jwt), dto);
        if(blog == null) return Result.fail("Failed to edit");
        return Result.success(blog);
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@RequestHeader(value = "Authorization") String jwt, @PathVariable("id") Integer blogId) {
        if(blogService.delete(JwtUtil.getUserId(jwt), blogId)) return Result.success("Deleted successfully");
        return Result.fail("Failed to delete");
    }

    @GetMapping("/{id}")
    public Result view(@PathVariable("id") Integer blogId) {
        return Result.success(blogService.view(blogId));
    }

    @GetMapping(value = "/list")
    public Result list(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                       @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                       @RequestParam(value = "opt") String opt, @RequestParam(value = "val") String val) {
        return Result.success(blogService.list(pageNum, pageSize, opt, val));
    }

}

