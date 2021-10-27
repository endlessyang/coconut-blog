package com.coconut.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.coconut.model.dto.CommentDTO;
import com.coconut.model.entity.Comment;
import com.coconut.model.vo.CommentVO;
import java.util.List;

public interface CommentService extends IService<Comment> {

    List<CommentVO> getCommentVObyBlogId(Integer blogId);

    void add(Integer userId, CommentDTO dto);

}
