package com.coconut.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coconut.mapper.CommentMapper;
import com.coconut.model.dto.CommentDTO;
import com.coconut.model.entity.Comment;
import com.coconut.model.entity.User;
import com.coconut.model.vo.CommentVO;
import com.coconut.service.CommentService;
import com.coconut.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Autowired
    UserService userService;

    @Override
    public List<CommentVO> getCommentVObyBlogId(Integer blogId) {
        List<Comment> comments = list(new QueryWrapper<Comment>().eq("blog_id", blogId).orderByDesc("create_time"));
        List<CommentVO> commentVOs = new ArrayList<>();
        for(Comment comment : comments) {
            User user = userService.getById(comment.getUserId());
            commentVOs.add(new CommentVO(comment.getContent(), user.getUsername(), comment.getCreateTime()));
        }
        return commentVOs;
    }

    @Override
    public void add(Integer userId, CommentDTO dto) {
        save(Comment.builder().content(dto.getContent()).blogId(dto.getBlogId()).userId(userId).build());
    }

}
