package com.coconut.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.coconut.model.entity.Comment;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentMapper extends BaseMapper<Comment> {

}
